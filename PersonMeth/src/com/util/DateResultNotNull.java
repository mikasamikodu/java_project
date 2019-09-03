package com.util;

import com.entity.Sa_Opperson_Oporg;
import com.entity.Saoporg;
import com.entity.Saopperson;
import com.entity.Vendor;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateResultNotNull
{
  public String notNull(Saopperson emp, Saoporg dept)
  {
    String str = "";
    String SorgkindID = "psm";
    if (SorgkindID.equals(dept.getSorgKindId()))
    {
      if ((emp.getSid() == null) || (emp.getSid().trim().equals("")) || ("null".equals(emp.getSid()))) {
        str = str + "sid不能为空;";
      }
      if ((emp.getSnameid() == null) || (emp.getSnameid().trim().equals("")) || ("null".equals(emp.getSnameid())))
      {
        str = str + "sNameID不能为空";
      }
      else
      {
        String scode = emp.getSnameid();
        if (scode.length() < 4) {
          scode = scode + "ceshi";
        }
        String StrScode = scode.substring(0, 4);
        if (StrScode.equals("null")) {
          str = str + "snameID:" + emp.getSnameid() + ",格式不正确";
        }
      }
      if ((emp.getJoinDate() == null) || (emp.getJoinDate().trim().equals("")) || ("null".equals(emp.getJoinDate()))) {
        emp.setJoinDate(new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date()));
      }
      if ((emp.getSvalIdState() == null) || ("null".equals(emp.getSvalIdState())) || 
        ("".equals(emp.getSvalIdState().trim()))) {
        str = str + "SvalIdState不能为空;";
      }
      if ((emp.getSidCard() == null) || (emp.getSidCard().trim().equals("")) || 
        ("null".equals(emp.getSidCard().trim()))) {
        str = str + "SidCard不能为空;";
      }
      if ((emp.getSname() == null) || (emp.getSname().trim().equals("")) || ("null".equals(emp.getSname().trim()))) {
        str = str + "Sname不能为空;";
      }
      if ((dept.getSorgKindId() == null) || (dept.getSorgKindId().trim().equals("")) || 
        ("null".equals(dept.getSorgKindId().trim()))) {
        str = str + "SorgKindId不能为空;";
      }
      if ((dept.getDepartment() == null) || (dept.getDepartment().trim().equals("")) || 
        ("null".equals(dept.getDepartment()))) {
        str = str + "Department不能为空;";
      }
      if ((dept.getDepartmentID() == null) || (dept.getDepartmentID().trim().equals("")) || 
        ("null".equals(dept.getDepartmentID().trim()))) {
        str = str + "DepartMentID不能为空;";
      }
      if ((dept.getOrganization() == null) || (dept.getOrganization().trim().equals("")) || 
        ("null".equals(dept.getOrganization().trim()))) {
        str = str + "Organization不能为空;";
      }
      if ((dept.getOrganizationID() == null) || (dept.getOrganizationID().trim().equals("")) || 
        ("null".equals(dept.getOrganizationID().trim()))) {
        str = str + "OrganizationID不能为空;";
      }
    }
    else
    {
      str = str + "请确认人员标识SorgkindID是否填写正确（psm）：" + dept.getSorgKindId();
    }
    return str;
  }
  
  public String OporgnotNull(Sa_Opperson_Oporg soo)
  {
    String strOporg = "";
    if ("ogn".equals(soo.getSorgkindid()))
    {
      if ((soo.getSfname() == null) || (soo.getSfname().trim().equals("")) || ("null".trim().equals(soo.getSfname()))) {
        strOporg = strOporg + "ORGANIZATION:不能为空";
      }
      if ((soo.getSfid() == null) || (soo.getSfid().trim().equals("")) || ("null".trim().equals(soo.getSfid()))) {
        strOporg = strOporg + "ORGANIZATIONID:不能为空";
      }
      if ((soo.getCompanyname() == null) || (soo.getCompanyname().trim().equals("")) || 
        ("null".trim().equals(soo.getCompanyname()))) {
        strOporg = strOporg + "CompanyName:不能为空";
      }
      if ((soo.getCompanyid() == null) || (soo.getCompanyid().trim().equals("")) || 
        ("null".trim().equals(soo.getCompanyid()))) {
        strOporg = strOporg + "CompanyID:不能为空";
      }
      if ((soo.getSvalidstate() == null) || (soo.getSvalidstate().trim().equals("")) || 
        ("null".trim().equals(soo.getSvalidstate()))) {
        strOporg = strOporg + "SvalidState:不能为空";
      }
    }
    if ("dpt".equals(soo.getSorgkindid()))
    {
      if ((soo.getCompanyname() == null) || (soo.getCompanyname().trim().equals("")) || 
        ("null".equals(soo.getCompanyname()))) {
        strOporg = strOporg + "CompanyName:不能为空;";
      }
      if ((soo.getCompanyid() == null) || (soo.getCompanyid().trim().equals("")) || 
        ("null".equals(soo.getCompanyid()))) {
        strOporg = strOporg + "CompanyID:不能为空;";
      }
      if ((soo.getDepartmentid() == null) || ("".trim().equals(soo.getDepartmentid())) || 
        ("null".equals(soo.getDepartmentid()))) {
        strOporg = strOporg + "DepartmentID:不能为空;";
      }
      if ((soo.getDepartmentname() == null) || (soo.getDepartmentname().trim().equals("")) || 
        ("null".equals(soo.getDepartmentname()))) {
        strOporg = strOporg + "DepartmentName:不能为空;";
      }
      if ((soo.getSvalidstate() == null) || (soo.getSvalidstate().trim().equals("")) || 
        ("null".trim().equals(soo.getSvalidstate()))) {
        strOporg = strOporg + "SvalidState:不能为空;";
      }
    }
    if ((soo.getSorgkindid() == null) || ("".trim().equals(soo.getSorgkindid())) || 
      ("null".trim().equals(soo.getSorgkindid()))) {
      strOporg = "请确认公司/部门标识SorgkindID是否填写正确（ogn/dpt）：" + soo.getSorgkindid();
    }
    return strOporg;
  }
  
  public String validationOgn(Sa_Opperson_Oporg soo)
  {
    String validationBool = "true";
    
    String companyid = soo.getCompanyid();
    String companyName = soo.getCompanyname();
    if ("20000002".equals(companyid)) {
      companyName = "国家开发投资公司";
    } else if ("20000052".equals(companyid)) {
      companyName = "国投电力控股股份有限公司";
    } else if ("20000654".equals(companyid)) {
      companyName = "国投矿业投资有限公司";
    } else if ("20000208".equals(companyid)) {
      companyName = "国投交通控股有限公司";
    } else if ("20002041".equals(companyid)) {
      companyName = "国投生物科技投资有限公司";
    } else if ("20000320".equals(companyid)) {
      companyName = "国投资本控股有限公司";
    } else if ("20000325".equals(companyid)) {
      companyName = "国投泰康信托有限公司";
    } else if ("20000414".equals(companyid)) {
      companyName = "国投安信股份有限公司";
    } else if ("20000361".equals(companyid)) {
      companyName = "国投财务有限公司";
    } else if ("20000631".equals(companyid)) {
      companyName = "融实国际控股有限公司";
    } else if ("20000456".equals(companyid)) {
      companyName = "中国国投高新产业投资公司";
    } else if ("20000649".equals(companyid)) {
      companyName = "国投创益产业基金管理有限公司";
    } else if ("20001672".equals(companyid)) {
      companyName = "国投健康产业投资有限公司";
    } else if ("20000646".equals(companyid)) {
      companyName = "国投资产管理公司";
    } else if ("20000493".equals(companyid)) {
      companyName = "中国电子工程设计院";
    } else if ("20000645".equals(companyid)) {
      companyName = "中投咨询有限公司";
    } else if ("20000364".equals(companyid)) {
      companyName = "国投物业有限责任公司";
    } else if ("20000365".equals(companyid)) {
      companyName = "国投物业北京一分公司";
    } else if ("20000366".equals(companyid)) {
      companyName = "国投物业北京二分公司";
    } else if ("20000367".equals(companyid)) {
      companyName = "国投物业北京三分公司";
    } else if ("20000368".equals(companyid)) {
      companyName = "国投物业上海分公司";
    } else if ("20000369".equals(companyid)) {
      companyName = "五分公司";
    } else if ("20000622".equals(companyid)) {
      companyName = "中国成套设备进出口（集团）总公司";
    } else if ("20000590".equals(companyid)) {
      companyName = "中成国际糖业股份有限公司";
    } else if ("20000568".equals(companyid)) {
      companyName = "中成进出口股份有限公司";
    } else if ("20001669".equals(companyid)) {
      companyName = "国投智能科技有限公司";
    } else if ("20001673".equals(companyid)) {
      companyName = "广州国投悦康美邸养老服务有限公司";
    } else if ("20000266".equals(companyid)) {
      companyName = "国投高科技投资有限公司";
    } else if ("20000829".equals(companyid)) {
      companyName = "国投海外投资有限公司";
    } else if ("20000856".equals(companyid)) {
      companyName = "国投颐康（北京）养老投资有限公司";
    } else if ("20000855".equals(companyid)) {
      companyName = "国投悦康养老服务有限公司";
    } else if ("20000632".equals(companyid)) {
      companyName = "国投融资租赁有限公司";
    } else if ("20001864".equals(companyid)) {
      companyName = "国投微藻生物科技中心";
    } else if ("20002575".equals(companyid)) {
      companyName = "国投生物能源（铁岭）有限公司";
    } else if ("20005406".equals(companyid)) {
      companyName = "国投生物能源（海伦）有限公司";
    } else if ("20000650".equals(companyid)) {
      companyName = "国投人力资源服务有限公司";
    } else if ("20005392".equals(companyid)) {
        companyName = "国投健康（常州）养老服务有限公司";
    } else if ("20004555".equals(companyid)) {
        companyName = "国投检验检测认证有限公司";
    } else {
      validationBool = "false";
    }
    soo.setCompanyname(companyName);
    return validationBool;
  }
  
  public String validationVendor(Vendor vendor)
  {
    String validationBool = "true";
    
    String companyid = vendor.getOrg_id();
    String companyName = vendor.getOrg_name();
    if ("20000002".equals(companyid)) {
      companyName = "国家开发投资公司";
    } else if ("20000052".equals(companyid)) {
      companyName = "国投电力控股股份有限公司";
    } else if ("20000654".equals(companyid)) {
      companyName = "国投矿业投资有限公司";
    } else if ("20000208".equals(companyid)) {
      companyName = "国投交通控股有限公司";
    } else if ("20002041".equals(companyid)) {
      companyName = "国投生物科技投资有限公司";
    } else if ("20000320".equals(companyid)) {
      companyName = "国投资本控股有限公司";
    } else if ("20000325".equals(companyid)) {
      companyName = "国投泰康信托有限公司";
    } else if ("20000414".equals(companyid)) {
      companyName = "国投安信股份有限公司";
    } else if ("20000361".equals(companyid)) {
      companyName = "国投财务有限公司";
    } else if ("20000631".equals(companyid)) {
      companyName = "融实国际控股有限公司";
    } else if ("20000456".equals(companyid)) {
      companyName = "中国国投高新产业投资公司";
    } else if ("20000649".equals(companyid)) {
      companyName = "国投创益产业基金管理有限公司";
    } else if ("20001672".equals(companyid)) {
      companyName = "国投健康产业投资有限公司";
    } else if ("20000646".equals(companyid)) {
      companyName = "国投资产管理公司";
    } else if ("20000493".equals(companyid)) {
      companyName = "中国电子工程设计院";
    } else if ("20000645".equals(companyid)) {
      companyName = "中投咨询有限公司";
    } else if ("20000364".equals(companyid)) {
      companyName = "国投物业有限责任公司";
    } else if ("20000365".equals(companyid)) {
      companyName = "国投物业北京一分公司";
    } else if ("20000366".equals(companyid)) {
      companyName = "国投物业北京二分公司";
    } else if ("20000367".equals(companyid)) {
      companyName = "国投物业北京三分公司";
    } else if ("20000368".equals(companyid)) {
      companyName = "国投物业上海分公司";
    } else if ("20000369".equals(companyid)) {
      companyName = "五分公司";
    } else if ("20000622".equals(companyid)) {
      companyName = "中国成套设备进出口（集团）总公司";
    } else if ("20000590".equals(companyid)) {
      companyName = "中成国际糖业股份有限公司";
    } else if ("20000568".equals(companyid)) {
      companyName = "中成进出口股份有限公司";
    } else if ("20001669".equals(companyid)) {
      companyName = "国投智能科技有限公司";
    } else if ("20001673".equals(companyid)) {
      companyName = "广州国投悦康美邸养老服务有限公司";
    } else if ("20000266".equals(companyid)) {
      companyName = "国投高科技投资有限公司";
    } else if ("20000829".equals(companyid)) {
      companyName = "国投海外投资有限公司";
    } else if ("20000856".equals(companyid)) {
      companyName = "国投颐康（北京）养老投资有限公司";
    } else if ("20000855".equals(companyid)) {
      companyName = "国投悦康养老服务有限公司";
    } else if ("20000632".equals(companyid)) {
      companyName = "国投融资租赁有限公司";
    } else if ("20001864".equals(companyid)) {
      companyName = "国投微藻生物科技中心";
    } else if ("20002575".equals(companyid)) {
      companyName = "国投生物能源（铁岭）有限公司";
    } else if ("20005406".equals(companyid)) {
      companyName = "国投生物能源（海伦）有限公司";
    } else if ("20000650".equals(companyid)) {
      companyName = "国投人力资源服务有限公司";
    } else if ("20005392".equals(companyid)) {
        companyName = "国投健康（常州）养老服务有限公司";
    } else if ("20004555".equals(companyid)) {
        companyName = "国投检验检测认证有限公司";
    } else {
      validationBool = "false";
    }
    vendor.setOrg_name(companyName);
    return validationBool;
  }
  
  public String vendorNull(Vendor vendor)
  {
    String vendorinfo = "";
    if (("".equals(vendor.getVendor_id())) || (vendor.getVendor_id() == null)) {
      vendorinfo = "Vendor_id;";
    }
    if (("".equals(vendor.getVendor_name())) || (vendor.getVendor_name() == null)) {
      vendorinfo = "Vendor_name;";
    }
    if (("".equals(vendor.getVendor_site_id())) || (vendor.getVendor_site_id() == null)) {
      vendorinfo = "vendor_site_id;";
    }
    if (("".equals(vendor.getVendor_site_name())) || (vendor.getVendor_site_name() == null)) {
      vendorinfo = "vendor_site_name;";
    }
    if (("".equals(vendor.getOrg_id())) || (vendor.getOrg_id() == null)) {
      vendorinfo = "org_id;";
    }
    if (("".equals(vendor.getOrg_name())) || (vendor.getOrg_name() == null)) {
      vendorinfo = "org_name;";
    }
    return vendorinfo;
  }
}
