package com.tonto.hms.web.control.loupan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tonto.hms.dao.support.Page;
import com.tonto.hms.model.Loupan;
import com.tonto.hms.service.loupan.LoupanService;
import com.tonto.hms.web.control.JsonResponse;

@Controller
@RequestMapping("/loupan")
public class LoupanAction {
	
	@Autowired
	LoupanService loupanService;
	
	@RequestMapping("/view")
	public ModelAndView view()
	{
		return new ModelAndView("loupan/loupan");
	}
	
	@RequestMapping("/page/{pageNo}/{pageSize}")
	public ModelAndView getLoupanPage(@PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize,@ModelAttribute("loupan")Loupan loupan)
	{
		Page<Loupan> page=loupanService.searchLoupanByPage(pageNo,pageSize>0?pageSize:20,loupan);
		
		ModelAndView model=new ModelAndView("loupan/loupanPage");
		model.addObject("pager", page);
		model.addObject("loupans", page.getResult());		
		return model;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/page/json/{pageNo}")
	@ResponseBody
	public JsonResponse<Page<Loupan>> getLoupanPageJson(@PathVariable("pageNo") int pageNo,@ModelAttribute("loupan")Loupan loupan)
	{
		Page<Loupan> page=loupanService.searchLoupanByPage(pageNo,10,loupan);
		JsonResponse<Page<Loupan>> response= (JsonResponse<Page<Loupan>>) JsonResponse.getSuccessResponse();
		response.setResult(page);
		return response;
	} 
	
	@RequestMapping("/toSelect")
	public String toSelect()
	{
		return "loupan/loupanSelect";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String toAdd()
	{
		return "loupan/loupanAdd";
	}
	
	@RequestMapping("/add/ajax")
	@ResponseBody
	public JsonResponse<?> addLoupanAjax(@ModelAttribute("loupan")Loupan loupan)
	{
		loupanService.saveLoupan(loupan);
		return JsonResponse.getSuccessResponse();
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public ModelAndView toUpdate(@PathVariable("id") int id)
	{
		Loupan loupan=loupanService.getLoupan(id);
		return new ModelAndView("loupan/loupanUpdate","loupan",loupan);
	}
	
	@RequestMapping("/update/ajax")
	@ResponseBody
	public JsonResponse<?> updateLoupanAjax(@ModelAttribute("loupan")Loupan loupan)
	{
		loupanService.updateLoupan(loupan);
		return JsonResponse.getSuccessResponse();
	}
	
	@RequestMapping("/delete/ajax/{id}")
	@ResponseBody
	public JsonResponse<?> deleteLoupanAjax(@PathVariable("id") int id)
	{
		loupanService.deleteLoupan(id);
		return JsonResponse.getSuccessResponse();
	}
}
