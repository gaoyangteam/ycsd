package com.jspxcms.core.web.fore;

import com.jspxcms.common.web.Servlets;
import com.jspxcms.common.web.Validations;
import com.jspxcms.core.constant.Constants;
import com.jspxcms.core.domain.Info;
import com.jspxcms.core.domain.Node;
import com.jspxcms.core.domain.Site;
import com.jspxcms.core.service.InfoQueryService;
import com.jspxcms.core.service.NodeBufferService;
import com.jspxcms.core.service.NodeQueryService;
import com.jspxcms.core.service.SiteService;
import com.jspxcms.core.support.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * NodeController
 * 
 * @author liufang
 * 首页和栏目
 * index and node（栏目）
 * cover_*.html 是封面模板
 * list_*.html 是列表模板
 */
@Controller
public class NodeController {
	@RequestMapping(value = { "/", "/index.jspx" })
	public String index(HttpServletRequest request,
			HttpServletResponse response, org.springframework.ui.Model modelMap) {
		return index(null, request, response, modelMap);
	}

	@RequestMapping(value = Constants.SITE_PREFIX_PATH + ".jspx")
	public String index(@PathVariable String siteNumber,
			HttpServletRequest request, HttpServletResponse response,
			org.springframework.ui.Model modelMap) {
		siteResolver.resolveSite(siteNumber);
		Site site = Context.getCurrentSite();
		Response resp = new Response(request, response, modelMap);
		List<String> messages = resp.getMessages();
		Node node = query.findRoot(site.getId());
		if (!Validations.exist(node, messages, "Node", "root")) {
			return resp.badRequest();
		}
		Info infoComPro = infoQuery.get(284);
		modelMap.addAttribute("infoComPro", infoComPro);//公司简介
		Info infoComPh = infoQuery.get(286);
		modelMap.addAttribute("infoComPh", infoComPh);//公司理念
		Info infoComCoo = infoQuery.get(287);
		modelMap.addAttribute("infoComCoo", infoComCoo);//诚邀合作
		Info infoComXC = infoQuery.get(288);
		modelMap.addAttribute("infoComXC", infoComXC);//公司宣传
		for(int i=289;i<=293;i++){
			Info infoFood = infoQuery.get(i);
			modelMap.addAttribute("infoFood"+i, infoFood);//产品菜品
		}
		Info infoFood327 = infoQuery.get(327);
		modelMap.addAttribute("infoFood327", infoFood327);//产品菜品

		modelMap.addAttribute("node", node);
		modelMap.addAttribute("text", node.getText());
		ForeContext.setData(modelMap.asMap(), request);
		String template = Servlets.getParam(request, "template");
		if (StringUtils.isNotBlank(template)) {
			return template;
		} else {
			return node.getTemplate();
		}
	}

	@RequestMapping("/node/{id:[0-9]+}.jspx")
	public String node(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response, org.springframework.ui.Model modelMap) {
		return node(null, id, 1, request, response, modelMap);
	}

	@RequestMapping(Constants.SITE_PREFIX_PATH + "/node/{id:[0-9]+}.jspx")
	public String node(@PathVariable String siteNumber,
			@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response, org.springframework.ui.Model modelMap) {
		return node(siteNumber, id, 1, request, response, modelMap);
	}

	@RequestMapping("/node/{id:[0-9]+}_{page:[0-9]+}.jspx")
	public String node(@PathVariable Integer id, @PathVariable Integer page,
			HttpServletRequest request, HttpServletResponse response,
			org.springframework.ui.Model modelMap) {
		return node(null, id, page, request, response, modelMap);
	}

	@RequestMapping(Constants.SITE_PREFIX_PATH
			+ "/node/{id:[0-9]+}_{page:[0-9]+}.jspx")
	public String node(@PathVariable String siteNumber,
			@PathVariable Integer id, @PathVariable Integer page,
			HttpServletRequest request, HttpServletResponse response,
			org.springframework.ui.Model modelMap) {
		Node node = query.get(id);
		siteResolver.resolveSite(siteNumber, node);
		Site site = Context.getCurrentSite();
		Response resp = new Response(request, response, modelMap);
		List<String> messages = resp.getMessages();
		if (!Validations.exist(node, messages, "Node", id)) {
			return resp.badRequest();
		}
		if (!node.getSite().getId().equals(site.getId())) {
			site = node.getSite();
			Context.setCurrentSite(site);
		}
		String linkUrl = node.getLinkUrl();
		if (StringUtils.isNotBlank(linkUrl)) {
			return "redirect:" + linkUrl;
		}
		modelMap.addAttribute("node", node);
		modelMap.addAttribute("text", node.getText());
		modelMap.addAttribute("caipin",page);
		Map<String, Object> data = modelMap.asMap();
		ForeContext.setData(data, request);
		ForeContext.setPage(data, page, node);
		String template = Servlets.getParam(request, "template");
		if (StringUtils.isNotBlank(template)) {
			return template;
		} else {
			return node.getTemplate();
		}
	}

	@ResponseBody
	@RequestMapping(value = { "/node_views/{id:[0-9]+}.jspx",
			Constants.SITE_PREFIX_PATH + "/node_views/{id:[0-9]+}.jspx" })
	public String views(@PathVariable Integer id) {
		return Integer.toString(bufferService.updateViews(id));
	}

	@Autowired
	private SiteResolver siteResolver;
	@Autowired
	private SiteService siteService;
	@Autowired
	private NodeBufferService bufferService;
	@Autowired
	private NodeQueryService query;
	@Autowired
	private InfoQueryService infoQuery;
}
