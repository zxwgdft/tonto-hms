package com.tonto.hms.web.control.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tonto.hms.dao.support.Page;
import com.tonto.hms.model.House;
import com.tonto.hms.model.HouseDetail;
import com.tonto.hms.service.house.HouseService;
import com.tonto.hms.web.control.JsonResponse;

@Controller
@RequestMapping("/house")
public class HouseAction {
	
	@Autowired
	HouseService houseService;
	
	@RequestMapping("/view")
	public ModelAndView view()
	{
		return new ModelAndView("house/house");
	}
	
	
	@RequestMapping("/page/{pageNo}")
	public ModelAndView getHousePage(@PathVariable("pageNo") int pageNo,@ModelAttribute("houseDetail")HouseDetail houseDetail)
	{
		Page<HouseDetail> page=houseService.searchHouseByPage(pageNo,20,houseDetail);
		
		ModelAndView model=new ModelAndView("house/housePage");
		model.addObject("pager", page);
		model.addObject("houses", page.getResult());		
		return model;
	}
	
	
	@RequestMapping("/toAdd")
	public String toAdd()
	{
		return "house/houseAdd";
	}
	
	@RequestMapping("/add/ajax")
	@ResponseBody
	public JsonResponse<?> addLoupanAjax(@ModelAttribute("house")House house)
	{
		houseService.saveHouse(house);
		return JsonResponse.getSuccessResponse();
	}
	
	@RequestMapping("/toUpdate/{id}")
	public ModelAndView toUpdate(@PathVariable("id") int id)
	{
		House house=houseService.getHouse(id);
		return new ModelAndView("house/houseUpdate","house",house);
	}
	
	@RequestMapping("/update/ajax")
	@ResponseBody
	public JsonResponse<?> updateHouseAjax(@ModelAttribute("house")House house)
	{
		houseService.updateHouse(house);
		return JsonResponse.getSuccessResponse();
	}
	
	@RequestMapping("/delete/ajax/{id}")
	@ResponseBody
	public JsonResponse<?> deleteHouseAjax(@PathVariable("id") int id)
	{
		houseService.deleteHouse(id);
		return JsonResponse.getSuccessResponse();
	}
}
