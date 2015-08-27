package com.tonto.hms.service.district;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tonto.hms.model.District;
import com.tonto.hms.util.TreeNode;

@Component
public class DistrictManager {
	
	@Autowired
	DistrictService districtService;
	
	private Map<Integer,TreeNode<District,Integer>> districtNodeMap;
	private Map<Integer,TreeNode<District,Integer>> provinceMap;
	private Map<Integer,District> districtMap;
	private final static int CODE_CHINA=100;
	
	
	
	@PostConstruct
	public void init()
	{
		List<District> districts=districtService.getAllDistrict();
		
		districtMap=new HashMap<Integer,District>();
		districtNodeMap=new HashMap<Integer,TreeNode<District,Integer>>();
		provinceMap=new HashMap<Integer,TreeNode<District,Integer>>();
		
		
		for(District district:districts)
		{
			Integer key=district.getCode();
			TreeNode<District,Integer> districtNode=new TreeNode<District,Integer>(district,key);
			districtNodeMap.put(key, districtNode);
			if(district.getParentCode()==CODE_CHINA)
			{
				provinceMap.put(key, districtNode);
			}
			districtMap.put(key, district);
		}
		
		for(TreeNode<District,Integer> node:districtNodeMap.values())
		{
			Integer pCode=node.getValue().getParentCode();
			if(pCode!=CODE_CHINA)
			{
				TreeNode<District,Integer> pNode=districtNodeMap.get(pCode);
				if(pNode!=null)
				{
					pNode.addChildNode(node);
					node.setParent(pNode);
				}
			}
		}
		
		JsWriter.write(provinceMap);
						
		manager=this;
	}
	
	public static TreeNode<District,Integer> getDistrictNode(Integer key)
	{
		return manager.districtNodeMap.get(key);
	}
	
	public static District getDistrict(Integer code)
	{
		return manager.districtMap.get(code);
	}
	
	private static DistrictManager manager;
	
}
