package com.tonto.hms.service.district;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import com.tonto.hms.model.District;
import com.tonto.hms.util.TreeNode;
import com.tonto.hms.util.WebUtil;

/**
 * 生成区域树形数据的js页面
 * @author Tonto
 *
 */
public class JsWriter {
	
	public static void write(Map<Integer,TreeNode<District,Integer>> provinceMap)
	{
		OutputStreamWriter writer=null;
		try {
			writer=new OutputStreamWriter(new FileOutputStream(WebUtil.getWebRootPath()+"/js/common/district.js"),"UTF-8");
			writer.append("var district_map=");	
			writerDistrict2js(provinceMap,writer);
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(writer!=null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	private static void writerDistrict2js(Map<Integer,TreeNode<District,Integer>> districtMap,Writer writer) throws IOException
	{
		writer.append("[");
		boolean isFirst=true;
		for(TreeNode<District,Integer> node:districtMap.values())
		{
			if(isFirst)
			{
				writer.append("{key:");
				isFirst=false;
			}
			else
			{
				writer.append(",{key:");
			}
			
			writer.append(node.getKey().toString()).append(",value:'").append(node.getValue().getDistrictName()).append("'");
			if(!node.getChildren().isEmpty())
			{
				writer.append(",children:");
				writerDistrict2js(node.getChildren(),writer);
			}
			writer.append("}");
		}
		writer.append("]");
	}
}
