package com.example.super_springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.super_springboot.dto.SQL1;
import com.example.super_springboot.dto.response.claim_info;
import com.example.super_springboot.dto.response.inquiry_personal_information;
import com.example.super_springboot.dto.response.inquiry_benefit;
import com.example.super_springboot.dto.response.inquiry_claim_header;
import com.example.super_springboot.dto.response.inquiry_claim_detail;
import com.example.super_springboot.dto.response.member_info;
import com.example.super_springboot.entity.ADODB_LOGSQL;
import com.example.super_springboot.entity.ClClaim;
import com.example.super_springboot.entity.ClLine;
import com.example.super_springboot.entity.MrMember;
import com.example.super_springboot.entity.MrMemberPlan;
import com.example.super_springboot.entity.MrMemberPlanBenefit;
import com.example.super_springboot.entity.MrPolicy;
import com.example.super_springboot.entity.MrPolicyPlan;
import com.example.super_springboot.entity.MrPolicyPlanBenefit;
import com.example.super_springboot.entity.MrPolicyholder;
import com.example.super_springboot.entity.PdBenHead;
import com.example.super_springboot.entity.PdPlan;
import com.example.super_springboot.entity.PdPlanBenefit;
import com.example.super_springboot.entity.PdPlanLimit;
import com.example.super_springboot.repository.ADODB_LOGSQLRepository1;
import com.example.super_springboot.repository.CL_CLAIM_Repository;
import com.example.super_springboot.repository.CL_LINE_Repository;
import com.example.super_springboot.repository.MR_MEMBER_PLAN_BENEFIT_Repository;
import com.example.super_springboot.repository.MR_MEMBER_PLAN_Repository;
import com.example.super_springboot.repository.MR_MEMBER_Repository;
import com.example.super_springboot.repository.MR_POLICY_HOLDER_Repository;
import com.example.super_springboot.repository.MR_POLICY_PLAN_BENEFIT_Repository;
import com.example.super_springboot.repository.MR_POLICY_PLAN_Repository;
import com.example.super_springboot.repository.MR_POLICY_Repository;
import com.example.super_springboot.repository.PD_BEN_HEAD_Repository;
import com.example.super_springboot.repository.PD_PLAN_BENEFIT_Repository;
import com.example.super_springboot.repository.PD_PLAN_Repository;
import com.example.super_springboot.repository.PD_PLAN_LIMIT_Repository;
//import com.example.super_springboot.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
public class MyController {
    // @Autowired
    // private UserService userService;

    @Autowired
    private ADODB_LOGSQLRepository1 ADODB_LOGSQLRepository1;

    @Autowired
    private MR_MEMBER_Repository mr_member_repository;

    @Autowired
    private MR_POLICY_Repository mr_policy_repository;

    @Autowired
    private MR_MEMBER_PLAN_Repository mr_member_plan_repository;

    @Autowired
    private MR_POLICY_PLAN_Repository my_policy_plan_repository;

    @Autowired
    private MR_POLICY_HOLDER_Repository my_policy_holder_repository;

    @Autowired
    private PD_PLAN_Repository pd_plan_repository;

    @Autowired
    private MR_MEMBER_PLAN_BENEFIT_Repository mr_member_plan_benefit_repository;

    @Autowired
    private MR_POLICY_PLAN_BENEFIT_Repository my_policy_plan_benefit_repository;

    @Autowired
    private PD_PLAN_BENEFIT_Repository pd_plan_benefit_repository;

    @Autowired
    private PD_BEN_HEAD_Repository pd_ben_head_repository;

    @Autowired
    private CL_CLAIM_Repository cl_claim_repository;

    @Autowired
    private CL_LINE_Repository cl_line_repository;

    @Autowired
    private PD_PLAN_LIMIT_Repository pd_plan_limit_repository;

    @GetMapping("/")
    public String home() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/mycontroller")
    public String isara() {
        return "Greetings from mycontroller";
    }

    @GetMapping(path="/checkdb")
    public List<SQL1> checkdb() {
        List<SQL1> retval = new ArrayList<>();
        SQL1 sql_obj = new SQL1();
        List<ADODB_LOGSQL> list_obj = (List<ADODB_LOGSQL>) ADODB_LOGSQLRepository1.Get_SQL0_SQL1();

        for (int i = 0 ; i < list_obj.size() ; i++) {
            sql_obj.setSQL1(list_obj.get(i).getSQL1());
            retval.add(sql_obj);
        }
     return retval;
    }

    @GetMapping(path="/checkdb1")
    public List<SQL1> checkdb1() {
        return checkdb();
    }

    @PostMapping(path="/inquiry_personal_information")
    public member_info inquiry_personal_information(@RequestParam Map<String, String> requestParams) {

        //String MBR_NO = requestParams.get("MBR_NO");
        String ID_CARD_NO = requestParams.get("ID_CARD_NO");
        //String TIN = requestParams.get("TIN");
        member_info member_info_obj = new member_info();

        List<MrMember> member_obj = (List<MrMember>) mr_member_repository.get_MEMB_OID(ID_CARD_NO);
        if (member_obj.size() >= 1)
        {
            member_info_obj.setMember_no(member_obj.get(0).getMbrNo());
            member_info_obj.setEmpid(member_obj.get(0).getMbrNo());
            member_info_obj.setMemb_oid(member_obj.get(0).getMemb_oid());
            member_info_obj.setName(member_obj.get(0).getMbr_first_name() + " " +  member_obj.get(0).getMbr_last_name());
            member_info_obj.setDate_of_birth(member_obj.get(0).getDOB());
            member_info_obj.setAccount_no(member_obj.get(0).getCL_PAY_ACCT_NO());
            member_info_obj.setMember_type(member_obj.get(0).getSCMA_OID_MBR_TYPE());
            member_info_obj.setMarital_status(member_obj.get(0).getSCMA_OID_CIVIL_STATUS());
            member_info_obj.setGender(member_obj.get(0).getSCMA_OID_SEX());
            member_info_obj.setId_card(member_obj.get(0).getID_CARD_NO());
            member_info_obj.setMember_refno(member_obj.get(0).getCUSM_REF_NO());
            //member_info_obj.setPplan(pdplan_obj.get(0).getPlan_no().trim());
            member_info_obj.setPplan(member_obj.get(0).getPLAN_NO());
            List<MrMemberPlan> member_plan_obj = (List<MrMemberPlan>) mr_member_plan_repository.get_POPL_OID(member_obj.get(0).getMemb_oid());
            if (member_plan_obj.size() >= 1)
            {
                member_info_obj.setStart_date(member_plan_obj.get(0).getEff_date());
                member_info_obj.setEnd_date(member_plan_obj.get(0).getExp_date());

                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"); // Adjust format as needed
                LocalDate TargetDate = LocalDate.parse(member_info_obj.getEnd_date(), formatter);

                if (TargetDate.isAfter(today))
                    member_info_obj.setStatus("Active");
                else
                    member_info_obj.setStatus("Inactive");

                member_info_obj.setPOPL_OID(member_plan_obj.get(0).getPoplOid());
                List<MrPolicyPlan> policy_plan_obj = (List<MrPolicyPlan>) my_policy_plan_repository.get_POCY_OID(member_plan_obj.get(0).getPoplOid());
                if (policy_plan_obj.size() >= 1)
                {
                    member_info_obj.setPOCY_OID(policy_plan_obj.get(0).getPocyOid());
                    List<MrPolicy> policy_obj = (List<MrPolicy>) mr_policy_repository.get_policy_no(policy_plan_obj.get(0).getPocyOid());
                    if (policy_obj.size() >= 1)
                    {
                        member_info_obj.setPolicy_no(policy_obj.get(0).getPocyNo());
                        member_info_obj.setGroup_id(policy_obj.get(0).getPocyNo());
                        member_info_obj.setRef_policyno(policy_obj.get(0).getLMG_NO());
                        List<MrPolicyholder> policy_holder_obj = (List<MrPolicyholder>) my_policy_holder_repository.get_poho_name(policy_obj.get(0).getPohoOid());
                        if (policy_holder_obj.size() >= 1) {
                            member_info_obj.setBranch(policy_holder_obj.get(0).getCustomer_branch());
                            member_info_obj.setPolicy_holder(policy_holder_obj.get(0).getPoho_name_1() + " " + policy_holder_obj.get(0).getPoho_name_2());
                            member_info_obj.setCompany_name(policy_holder_obj.get(0).getPoho_name_1() + " " + policy_holder_obj.get(0).getPoho_name_2());
                            member_info_obj.setPolicy_type(policy_holder_obj.get(0).getSCMA_OID_POHO_TYPE());
                            List<PdPlan> pdplan_obj = (List<PdPlan>) pd_plan_repository.get_PLAN_ID(policy_plan_obj.get(0).getPlanOid());
                            if (pdplan_obj.size() >= 1) {
                                member_info_obj.setToc(pdplan_obj.get(0).getPlanId());
                                //member_info_obj.setPplan(pdplan_obj.get(0).getPlan_no().trim());
                            } else {
                                return member_info_obj;
                            }
                        }
                        else
                        {
                            return member_info_obj;
                        }
                    }
                    else
                    {
                        return member_info_obj;
                    }
                }
                else
                {
                    return member_info_obj;
                }
            }
            else
            {
                return member_info_obj;
            }
            return member_info_obj;
        }
        else
        {
            return member_info_obj;
        }
    }

    @PostMapping(path="/inquiry_personal_information2")
    //public List<member_info> inquiry_personal_information2(@RequestParam Map<String, String> requestParams) {
    public List<inquiry_personal_information> inquiry_personal_information2(@RequestParam Map<String, String> requestParams) {
        //List<member_info> member_info_list = new ArrayList<>();
        String upd_date = requestParams.get("upd_date");
        String seq = requestParams.get("seq");
        List<inquiry_personal_information> member_info_list = new ArrayList<>();

        LocalDate today = LocalDate.now();
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy"); // Adjust format as needed
        // today = LocalDate.parse(today.toString(), formatter);
        //String today_str = String.valueOf(today.getDayOfMonth()) + String.valueOf(today.getMonthValue()) + String.valueOf(today.getYear()); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        //today = today.format(formatter);
        String today_str = today.format(formatter);

        //formatter = DateTimeFormatter.ofPattern("dd-MMM-yy", Locale.ENGLISH);
        formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MMM-yy")
                .toFormatter(Locale.getDefault());
        String daybefore="";
        try {
            Date target_date = new SimpleDateFormat("dd-MMM-yy").parse(upd_date);
            LocalDate previous_date = LocalDate.of(target_date.getYear(), target_date.getMonth()+1, target_date.getDate());
            previous_date = previous_date.minusDays(1);
            daybefore = previous_date.format(formatter);
            daybefore = daybefore.toUpperCase();
            //daybefore = previous_date.toString();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //LocalDate.of(target_date.getYear(), target_date.getMonth()+1, target_date.getDate());
        // LocalDate previous_date = target_date.minusDays(1);
        // String daybefore = previous_date.format(formatter);

        Long begining_seq = (long) (((Integer.valueOf(seq)) - 1) * 1000) + 1;
        Long ending_seq = (long) Integer.valueOf(seq) * 1000;

        //System.out.println(upd_date + "=>" + daybefore + "=>" + begining_seq + "=>" + ending_seq + "=>" + today_str);

        System.out.println(upd_date + "=>" + daybefore + "=>" + begining_seq + "=>" + ending_seq + "=>" + today_str);


        List<Long> thousand_members = (List<Long>) mr_member_repository.get_1000_active_members(upd_date,daybefore,today_str);
        for (int i=0 ; i < thousand_members.size() ; i++ )
        {
            if (( i < (begining_seq-1)) || (i > (ending_seq-1)))
             continue;

            //member_info member_info_obj = new member_info();
            inquiry_personal_information member_info_obj = new inquiry_personal_information();
            List<MrMember> member_obj = (List<MrMember>) mr_member_repository.get_MEMBER_From_MEMB_OID(thousand_members.get(i));
            member_info_obj.setMember_no(member_obj.get(0).getMbrNo());
            member_info_obj.setEmpid(member_obj.get(0).getMbrNo());
            //member_info_obj.setMemb_oid(member_obj.get(0).getMemb_oid());
            member_info_obj.setName(member_obj.get(0).getMbr_first_name() + " " +  member_obj.get(0).getMbr_last_name());
            member_info_obj.setDate_of_birth(member_obj.get(0).getDOB());
            member_info_obj.setAccount_no(member_obj.get(0).getCL_PAY_ACCT_NO());
            member_info_obj.setMember_type(member_obj.get(0).getSCMA_OID_MBR_TYPE());
            member_info_obj.setMarital_status(member_obj.get(0).getSCMA_OID_CIVIL_STATUS());
            member_info_obj.setGender(member_obj.get(0).getSCMA_OID_SEX());
            member_info_obj.setId_card(member_obj.get(0).getID_CARD_NO());
            member_info_obj.setMember_refno(member_obj.get(0).getCUSM_REF_NO());
            member_info_obj.setPplan(member_obj.get(0).getPLAN_NO());
            List<MrMemberPlan> member_plan_obj = (List<MrMemberPlan>) mr_member_plan_repository.get_POPL_OID(member_obj.get(0).getMemb_oid());
            if (member_plan_obj.size() >= 1)
            {
                member_info_obj.setStart_date(member_plan_obj.get(0).getEff_date());
                member_info_obj.setEnd_date(member_plan_obj.get(0).getExp_date());

                // LocalDate today = LocalDate.now();
                // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"); // Adjust format as needed
                // LocalDate TargetDate = LocalDate.parse(member_info_obj.getEnd_date(), formatter);

                // if (TargetDate.isAfter(today))
                //     member_info_obj.setStatus("Active");
                // else
                //     member_info_obj.setStatus("Inactive");

                member_info_obj.setStatus("Active");

                //member_info_obj.setPOPL_OID(member_plan_obj.get(0).getPoplOid());
                List<MrPolicyPlan> policy_plan_obj = (List<MrPolicyPlan>) my_policy_plan_repository.get_POCY_OID(member_plan_obj.get(0).getPoplOid());
                if (policy_plan_obj.size() >= 1)
                {
                    //member_info_obj.setPOCY_OID(policy_plan_obj.get(0).getPocyOid());
                    List<MrPolicy> policy_obj = (List<MrPolicy>) mr_policy_repository.get_policy_no(policy_plan_obj.get(0).getPocyOid());
                    if (policy_obj.size() >= 1)
                    {
                        //member_info_obj.setStart_date(member_plan_obj.get(0).getEff_date());
                        //member_info_obj.setEnd_date(policy_obj.get(0).getEXP_DATE().toString());
                        
                        member_info_obj.setPolicy_no(policy_obj.get(0).getPocyNo());
                        member_info_obj.setGroup_id(policy_obj.get(0).getPocyNo());
                        member_info_obj.setRef_policyno(policy_obj.get(0).getLMG_NO());
                        List<MrPolicyholder> policy_holder_obj = (List<MrPolicyholder>) my_policy_holder_repository.get_poho_name(policy_obj.get(0).getPohoOid());
                        if (policy_holder_obj.size() >= 1) {
                            member_info_obj.setBranch(policy_holder_obj.get(0).getCustomer_branch());
                            member_info_obj.setPolicy_holder(policy_holder_obj.get(0).getPoho_name_1() + " " + policy_holder_obj.get(0).getPoho_name_2());
                            member_info_obj.setCompany_name(policy_holder_obj.get(0).getPoho_name_1() + " " + policy_holder_obj.get(0).getPoho_name_2());
                            member_info_obj.setPolicy_type(policy_holder_obj.get(0).getSCMA_OID_POHO_TYPE());
                            List<PdPlan> pdplan_obj = (List<PdPlan>) pd_plan_repository.get_PLAN_ID(policy_plan_obj.get(0).getPlanOid());
                            if (pdplan_obj.size() >= 1) {
                                member_info_obj.setToc(pdplan_obj.get(0).getPlanId());
                                //member_info_obj.setPplan(pdplan_obj.get(0).getPlan_no().trim());
                            } else {
                                //return member_info_obj;
                                member_info_list.add(member_info_obj);
                                return member_info_list;
                            }
                        }
                        else
                        {
                            //return member_info_obj;
                            member_info_list.add(member_info_obj);
                            return member_info_list;
                        }
                    }
                    else
                    {
                        //return member_info_obj;
                        member_info_list.add(member_info_obj);
                        return member_info_list;
                    }
                }
                else
                {
                    //return member_info_obj;
                    member_info_list.add(member_info_obj);
                    return member_info_list;
                }
            }
            else
            {
                //return member_info_obj;
                member_info_list.add(member_info_obj);
                return member_info_list;
            }
            //return member_info_obj;
            member_info_list.add(member_info_obj);
            //return member_info_list;
        }
//        else
//        {
//            //return member_info_obj;
//            member_info_list.add(member_info_obj);
//            return member_info_list;
//        }
        return member_info_list;
    }

    @PostMapping(path="/inquiry_benefit")
    public member_info inquiry_benefit(@RequestParam Map<String, String> requestParams) {
        //String MBR_NO = requestParams.get("MBR_NO");
        String ID_CARD_NO = requestParams.get("ID_CARD_NO");
        //String TIN = requestParams.get("TIN");
        member_info member_info_obj = new member_info();
        List<MrMember> member_obj = (List<MrMember>) mr_member_repository.get_MEMB_OID(ID_CARD_NO);
        if (member_obj.size() >= 1)
        {
            member_info_obj.setMember_no(member_obj.get(0).getMbrNo());
            member_info_obj.setEmpid(member_obj.get(0).getMbrNo());
            member_info_obj.setMemb_oid(member_obj.get(0).getMemb_oid());
            member_info_obj.setName(member_obj.get(0).getMbr_first_name() + " " +  member_obj.get(0).getMbr_last_name());
            member_info_obj.setDate_of_birth(member_obj.get(0).getDOB());
            member_info_obj.setAccount_no(member_obj.get(0).getCL_PAY_ACCT_NO());
            member_info_obj.setMember_type(member_obj.get(0).getSCMA_OID_MBR_TYPE());
            member_info_obj.setMarital_status(member_obj.get(0).getSCMA_OID_CIVIL_STATUS());
            member_info_obj.setGender(member_obj.get(0).getSCMA_OID_SEX());
            member_info_obj.setId_card(member_obj.get(0).getID_CARD_NO());
            member_info_obj.setMember_refno(member_obj.get(0).getCUSM_REF_NO());
            List<MrMemberPlan> member_plan_obj = (List<MrMemberPlan>) mr_member_plan_repository.get_MEPL_OID(member_obj.get(0).getMemb_oid());
            if (member_plan_obj.size() >= 1)
            {
                member_info_obj.setStart_date(member_plan_obj.get(0).getEff_date());
                member_info_obj.setEnd_date(member_plan_obj.get(0).getExp_date());

                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"); // Adjust format as needed
                LocalDate TargetDate = LocalDate.parse(member_info_obj.getEnd_date(), formatter);

                if (TargetDate.isAfter(today))
                    member_info_obj.setStatus("Active");
                else
                    member_info_obj.setStatus("Inactive");

                member_info_obj.setPOPL_OID(member_plan_obj.get(0).getPoplOid());



                List<MrMemberPlanBenefit> member_plan_benefit_obj = (List<MrMemberPlanBenefit>) mr_member_plan_benefit_repository.get_POBE_OID(member_plan_obj.get(0).getMepl_Oid());
                if (member_plan_benefit_obj.size() >= 1)
                {
                  member_info_obj.setPOBE_OID(member_plan_benefit_obj.get(0).getPobeOid());
                  List<MrPolicyPlanBenefit> policy_plan_benefit_obj = (List<MrPolicyPlanBenefit>) my_policy_plan_benefit_repository.get_PLBE_OID(member_plan_benefit_obj.get(0).getPobeOid());
                  if (policy_plan_benefit_obj.size() >= 1)
                  {
                      member_info_obj.setPLBE_OID(policy_plan_benefit_obj.get(0).getPLBE_OID());
                      List<PdPlanBenefit> pd_plan_benefit_obj = (List<PdPlanBenefit>) pd_plan_benefit_repository.get_BEHD_OID(policy_plan_benefit_obj.get(0).getPLBE_OID());
                      if (pd_plan_benefit_obj.size() >= 1)
                      {
                          member_info_obj.setBEHD_OID(pd_plan_benefit_obj.get(0).getBehdOid());
                          List<PdBenHead> pd_ben_head_obj = (List<PdBenHead>) pd_ben_head_repository.get_BEN_HEAD(pd_plan_benefit_obj.get(0).getBehdOid());
                          if (pd_ben_head_obj.size() >= 1)
                          {
                              member_info_obj.setBEN_HEAD(pd_ben_head_obj.get(0).getBenHead());
                              member_info_obj.setCoverage(pd_ben_head_obj.get(0).getSCMA_OID_BEN_TYPE());
                          }
                          else
                          {
                              return member_info_obj;
                          }
                      }
                      else
                      {
                          return member_info_obj;
                      }

                  }
                  else
                  {
                      return member_info_obj;
                  }
                }
                else
                {
                    return member_info_obj;
                }

                List<MrPolicyPlan> policy_plan_obj = (List<MrPolicyPlan>) my_policy_plan_repository.get_POCY_OID(member_plan_obj.get(0).getPoplOid());
                if (policy_plan_obj.size() >= 1)
                {
                    member_info_obj.setPOCY_OID(policy_plan_obj.get(0).getPocyOid());
                    List<MrPolicy> policy_obj = (List<MrPolicy>) mr_policy_repository.get_policy_no(policy_plan_obj.get(0).getPocyOid());
                    if (policy_obj.size() >= 1)
                    {
                        member_info_obj.setPolicy_no(policy_obj.get(0).getPocyNo());
                        member_info_obj.setGroup_id(policy_obj.get(0).getPocyNo());
                        member_info_obj.setRef_policyno(policy_obj.get(0).getLMG_NO());
                        List<MrPolicyholder> policy_holder_obj = (List<MrPolicyholder>) my_policy_holder_repository.get_poho_name(policy_obj.get(0).getPohoOid());
                        if (policy_holder_obj.size() >= 1) {
                            member_info_obj.setBranch(policy_holder_obj.get(0).getCustomer_branch());
                            member_info_obj.setPolicy_holder(policy_holder_obj.get(0).getPoho_name_1() + " " + policy_holder_obj.get(0).getPoho_name_2());
                            member_info_obj.setCompany_name(policy_holder_obj.get(0).getPoho_name_1() + " " + policy_holder_obj.get(0).getPoho_name_2());
                            member_info_obj.setPolicy_type(policy_holder_obj.get(0).getSCMA_OID_POHO_TYPE());
                            List<PdPlan> pdplan_obj = (List<PdPlan>) pd_plan_repository.get_PLAN_ID(policy_plan_obj.get(0).getPlanOid());
                            if (pdplan_obj.size() >= 1) {
                                member_info_obj.setToc(pdplan_obj.get(0).getPlanId());
                                if (!(pdplan_obj.get(0).getPlan_desc()==null || pdplan_obj.get(0).getPlan_desc().isEmpty()))
                                {
                                    member_info_obj.setPplan(pdplan_obj.get(0).getPlan_desc().trim());
                                }
                                else
                                {
                                    member_info_obj.setPplan("");
                                }
                            } else {
                                return member_info_obj;
                            }
                        }
                        else
                        {
                            return member_info_obj;
                        }
                    }
                    else
                    {
                        return member_info_obj;
                    }
                }
                else
                {
                    return member_info_obj;
                }
            }
            else
            {
                return member_info_obj;
            }
            return member_info_obj;
        }
        else
        {
            return member_info_obj;
        }
    }

    @PostMapping(path="/inquiry_benefit2")
    //public member_info inquiry_benefit2(@RequestParam Map<String, String> requestParams) {
    public inquiry_benefit inquiry_benefit2(@RequestParam Map<String, String> requestParams) {
        String MBR_NO = requestParams.get("MBR_NO");
        //String ID_CARD_NO = requestParams.get("ID_CARD_NO");
        //String TIN = requestParams.get("TIN");

        //member_info member_info_obj = new member_info();
        inquiry_benefit member_info_obj = new inquiry_benefit();

        List<MrMember> member_obj = (List<MrMember>) mr_member_repository.get_MEMB_OID_From_MBR_NO(MBR_NO);
        if (member_obj.size() >= 1)
        {
            member_info_obj.setMember_no(member_obj.get(0).getMbrNo());
            // member_info_obj.setEmpid(member_obj.get(0).getMbrNo());
            // member_info_obj.setMemb_oid(member_obj.get(0).getMemb_oid());
            // member_info_obj.setName(member_obj.get(0).getMbr_first_name() + " " +  member_obj.get(0).getMbr_last_name());
            // member_info_obj.setDate_of_birth(member_obj.get(0).getDOB());
            // member_info_obj.setAccount_no(member_obj.get(0).getCL_PAY_ACCT_NO());
            // member_info_obj.setMember_type(member_obj.get(0).getSCMA_OID_MBR_TYPE());
            // member_info_obj.setMarital_status(member_obj.get(0).getSCMA_OID_CIVIL_STATUS());
            // member_info_obj.setGender(member_obj.get(0).getSCMA_OID_SEX());
            // member_info_obj.setId_card(member_obj.get(0).getID_CARD_NO());
            // member_info_obj.setMember_refno(member_obj.get(0).getCUSM_REF_NO());
            List<MrMemberPlan> member_plan_obj = (List<MrMemberPlan>) mr_member_plan_repository.get_MEPL_OID(member_obj.get(0).getMemb_oid());
            if (member_plan_obj.size() >= 1)
            {
                // member_info_obj.setStart_date(member_plan_obj.get(0).getEff_date());
                // member_info_obj.setEnd_date(member_plan_obj.get(0).getExp_date());

                // LocalDate today = LocalDate.now();
                // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"); // Adjust format as needed
                // LocalDate TargetDate = LocalDate.parse(member_info_obj.getEnd_date(), formatter);

                // if (TargetDate.isAfter(today))
                //     member_info_obj.setStatus("Active");
                // else
                //     member_info_obj.setStatus("Inactive");

                // member_info_obj.setPOPL_OID(member_plan_obj.get(0).getPoplOid());



                List<MrMemberPlanBenefit> member_plan_benefit_obj = (List<MrMemberPlanBenefit>) mr_member_plan_benefit_repository.get_POBE_OID(member_plan_obj.get(0).getMepl_Oid());
                if (member_plan_benefit_obj.size() >= 1)
                {
                    //member_info_obj.setPOBE_OID(member_plan_benefit_obj.get(0).getPobeOid());
                    List<MrPolicyPlanBenefit> policy_plan_benefit_obj = (List<MrPolicyPlanBenefit>) my_policy_plan_benefit_repository.get_PLBE_OID(member_plan_benefit_obj.get(0).getPobeOid());
                    if (policy_plan_benefit_obj.size() >= 1)
                    {
                        //member_info_obj.setPLBE_OID(policy_plan_benefit_obj.get(0).getPLBE_OID());
                        List<PdPlanBenefit> pd_plan_benefit_obj = (List<PdPlanBenefit>) pd_plan_benefit_repository.get_BEHD_OID(policy_plan_benefit_obj.get(0).getPLBE_OID());
                        if (pd_plan_benefit_obj.size() >= 1)
                        {
                            //member_info_obj.setBEHD_OID(pd_plan_benefit_obj.get(0).getBehdOid());
                            List<PdBenHead> pd_ben_head_obj = (List<PdBenHead>) pd_ben_head_repository.get_BEN_HEAD(pd_plan_benefit_obj.get(0).getBehdOid());
                            if (pd_ben_head_obj.size() >= 1)
                            {
                                member_info_obj.setBEN_HEAD(pd_ben_head_obj.get(0).getBenHead());
                                member_info_obj.setCoverage(pd_ben_head_obj.get(0).getSCMA_OID_BEN_TYPE());
                                member_info_obj.setBenefitName(pd_ben_head_obj.get(0).getSCMA_OID_BEN_TYPE());
                                member_info_obj.setBenefitUnit("THB");
                                member_info_obj.setCoverageVal(pd_ben_head_obj.get(0).getSCMA_OID_BEN_TYPE());
                            }
                            else
                            {
                                return member_info_obj;
                            }
                        }
                        else
                        {
                            return member_info_obj;
                        }

                    }
                    else
                    {
                        return member_info_obj;
                    }
                }
                else
                {
                    return member_info_obj;
                }

                List<MrPolicyPlan> policy_plan_obj = (List<MrPolicyPlan>) my_policy_plan_repository.get_POCY_OID(member_plan_obj.get(0).getPoplOid());
                if (policy_plan_obj.size() >= 1)
                {
                    //member_info_obj.setPOCY_OID(policy_plan_obj.get(0).getPocyOid());
                    List<MrPolicy> policy_obj = (List<MrPolicy>) mr_policy_repository.get_policy_no(policy_plan_obj.get(0).getPocyOid());
                    if (policy_obj.size() >= 1)
                    {
                        member_info_obj.setPolicy_no(policy_obj.get(0).getPocyNo());
                        //member_info_obj.setGroup_id(policy_obj.get(0).getPocyNo());
                        //member_info_obj.setRef_policyno(policy_obj.get(0).getLMG_NO());
                        List<MrPolicyholder> policy_holder_obj = (List<MrPolicyholder>) my_policy_holder_repository.get_poho_name(policy_obj.get(0).getPohoOid());
                        if (policy_holder_obj.size() >= 1) {
                            //member_info_obj.setBranch(policy_holder_obj.get(0).getCustomer_branch());
                            //member_info_obj.setPolicy_holder(policy_holder_obj.get(0).getPoho_name_1() + " " + policy_holder_obj.get(0).getPoho_name_2());
                            //member_info_obj.setCompany_name(policy_holder_obj.get(0).getPoho_name_1() + " " + policy_holder_obj.get(0).getPoho_name_2());
                            //member_info_obj.setPolicy_type(policy_holder_obj.get(0).getSCMA_OID_POHO_TYPE());

                            List<PdPlan> pdplan_obj = (List<PdPlan>) pd_plan_repository.get_PLAN_ID(policy_plan_obj.get(0).getPlanOid());
                            if (pdplan_obj.size() >= 1) {
                                member_info_obj.setToc(pdplan_obj.get(0).getPlanId());
                                if (!(pdplan_obj.get(0).getPlan_desc()==null || pdplan_obj.get(0).getPlan_desc().isEmpty()))
                                {
                                    member_info_obj.setPplan(pdplan_obj.get(0).getPlan_desc().trim());
                                }
                                else
                                {
                                    member_info_obj.setPplan("");
                                }
                            } else {
                                return member_info_obj;
                            }

                            //go to pd plan limit
                            List<PdPlanLimit> pdplan_limit_obj = (List<PdPlanLimit>) pd_plan_limit_repository.get_PLAN_LIMIT(policy_plan_obj.get(0).getPlanOid());

                            BigDecimal result =  BigDecimal.valueOf(0);
                            if (pdplan_limit_obj.get(0).getAmtDisYr() != null)
                            {
                                result = pdplan_limit_obj.get(0).getAmtDisYr();
                            }
                            for (int i = 1 ; i < pdplan_limit_obj.size() ; i++)
                            {
                                if (pdplan_limit_obj.get(i).getAmtDisYr() != null) {
                                    if (pdplan_limit_obj.get(i).getAmtDisYr().compareTo(result) > 0) {
                                        result = pdplan_limit_obj.get(i).getAmtDisYr();
                                    }
                                }
                            }
                            member_info_obj.setBenefitLimit(result.toString());

                            result =  BigDecimal.valueOf(-1);
//                            if (pdplan_limit_obj.get(0).getAmtDisYr() != null) {
//                                result = pdplan_limit_obj.get(0).getAmtYr();
//                            }
                            for (int i = 0 ; i < pdplan_limit_obj.size() ; i++)
                            {
                                if (pdplan_limit_obj.get(i).getAmtDisYr() != null) {
                                    if (pdplan_limit_obj.get(i).getAmtDisYr().compareTo(result) > 0) {
                                        result = pdplan_limit_obj.get(i).getAmtDisYr();
                                    }
                                }
                            }
                            member_info_obj.setAnnualLimit(result.toString());

                            int vis_year = 0;
                            if (pdplan_limit_obj.get(0).getVisYr() != null) {
                                vis_year = pdplan_limit_obj.get(0).getVisYr();
                            }
                            for (int i = 1 ; i < pdplan_limit_obj.size() ; i++)
                            {
                                if (pdplan_limit_obj.get(i).getVisYr() != null) {
                                    if (pdplan_limit_obj.get(i).getVisYr() > vis_year) {
                                        vis_year = pdplan_limit_obj.get(i).getVisYr();
                                    }
                                }
                            }
//                            if (vis_year > 0)
//                             member_info_obj.setAnnualUnit(String.valueOf(vis_year) + " Visits/Year");
                            int vis_day = 0;
                            if (pdplan_limit_obj.get(0).getVisDay() != null) {
                                vis_day = pdplan_limit_obj.get(0).getVisDay();
                            }
                            for (int i = 1 ; i < pdplan_limit_obj.size() ; i++)
                            {
                                if (pdplan_limit_obj.get(i).getVisDay() != null) {
                                    if (pdplan_limit_obj.get(i).getVisDay() > vis_day) {
                                        vis_day = pdplan_limit_obj.get(i).getVisDay();
                                    }
                                }
                            }
//                            if (vis_day > 0)
//                                member_info_obj.setAnnualUnit(String.valueOf(vis_day) + " Visits/Day");

                            String vis_year_day = "";
                            if (vis_year > 0)
                            {
                                //member_info_obj.setAnnualUnit(String.valueOf(vis_year) + " Visits/Year");
                                vis_year_day += String.valueOf(vis_year) + " Visit(s)/Year";

                                if (vis_day > 0)
                                {
                                    //member_info_obj.setAnnualUnit("," + String.valueOf(vis_day) + " Visits/Day");
                                    vis_year_day += ", " + String.valueOf(vis_day) + " Visit(s)/Day";
                                }
                                member_info_obj.setAnnualUnit(vis_year_day);
                            }
                            else
                            {
                                if (vis_day > 0)
                                {
                                    //member_info_obj.setAnnualUnit(String.valueOf(vis_day) + " Visits/Day");
                                    vis_year_day += String.valueOf(vis_day) + " Visit(s)/Day";
                                }
                                else
                                {
                                    //member_info_obj.setAnnualUnit("N/C");
                                    vis_year_day += "N/C";
                                }
                                member_info_obj.setAnnualUnit(vis_year_day);
                            }

                        }
                        else
                        {
                            return member_info_obj;
                        }
                    }
                    else
                    {
                        return member_info_obj;
                    }
                }
                else
                {
                    return member_info_obj;
                }
            }
            else
            {
                return member_info_obj;
            }
            return member_info_obj;
        }
        else
        {
            return member_info_obj;
        }
    }


    @PostMapping(path="/inquiry_claim_header")
    public claim_info inquiry_claim_header(@RequestParam Map<String, String> requestParams) {

        String CLAIM_NO = requestParams.get("CLAIM_NO");
        claim_info claim_info_obj = new claim_info();

        List<ClClaim> claim_obj = (List<ClClaim>) cl_claim_repository.get_CL_CLaim(CLAIM_NO);
        if (claim_obj.size() >= 1) {
            claim_info_obj.setClaim_no(claim_obj.get(0).getClNo());
            claim_info_obj.setStatus(claim_obj.get(0).getScmaOidClStatus());
            claim_info_obj.setStatus_th(claim_obj.get(0).getScmaOidClStatus());
            List<ClLine> cline_obj = (List<ClLine>) cl_line_repository.get_CL_Line(claim_obj.get(0).getClamOid());
            if (cline_obj.size() >= 1)
            {
                claim_info_obj.setStart(cline_obj.get(0).getIncurDateFrom().toString());
                claim_info_obj.setFinish(cline_obj.get(0).getIncurDateTo().toString());
                claim_info_obj.setClaim_type(cline_obj.get(0).getScmaOidClType());
                claim_info_obj.setBilled(cline_obj.get(0).getPresAmt());
                claim_info_obj.setAccepted(cline_obj.get(0).getAppAmt());
                claim_info_obj.setUnpaid(claim_info_obj.getBilled().subtract(claim_info_obj.getAccepted()));
                claim_info_obj.setCash_member(cline_obj.get(0).getPriorPaid());
                claim_info_obj.setTotal_paid(cline_obj.get(0).getPayAmt());
                claim_info_obj.setCoverage(cline_obj.get(0).getScmaOidBedType());
                claim_info_obj.setPayment_date(cline_obj.get(0).getPayDate().toString());
                claim_info_obj.setStatus_code(cline_obj.get(0).getScmaOidClLineStatus());
                claim_info_obj.setPay_to(cline_obj.get(0).getPayee());
            }
            else
            {
                return claim_info_obj;
            }
        }
        else
        {
            return claim_info_obj;
        }

        return claim_info_obj;
    }

    @PostMapping(path="/inquiry_claim_header2")
    //public List<claim_info> inquiry_claim_header2(@RequestParam Map<String, String> requestParams) {
    public List<inquiry_claim_header> inquiry_claim_header2(@RequestParam Map<String, String> requestParams) {
      
        String MBR_NO = requestParams.get("MBR_NO");
        List<MrMember> member_obj = (List<MrMember>) mr_member_repository.get_MEMB_OID_From_MBR_NO(MBR_NO);
        List<ClLine> cline_objs = (List<ClLine>) cl_line_repository.get_CL_Line_From_Memb(member_obj.get(0).getMemb_oid());
        
        //List<claim_info> claim_info_list = new ArrayList<>();  
        List<inquiry_claim_header> claim_info_list = new ArrayList<>();  
        
        for (int i = 0 ; i < cline_objs.size() ; i++)
        {
            //claim_info claim_info_obj = new claim_info();
            inquiry_claim_header claim_info_obj = new inquiry_claim_header();

            List<ClClaim> claim_obj = (List<ClClaim>) cl_claim_repository.get_CL_CLaim_From_CLAIM_OID(cline_objs.get(i).getClamOid());
            if (claim_obj.size() >= 1) {
                //claim_info_obj.setClaim_id(claim_obj.get(0).getClamOid());
                claim_info_obj.setClaim_no(claim_obj.get(0).getClNo());
                claim_info_obj.setStatus(claim_obj.get(0).getScmaOidClStatus());
                claim_info_obj.setStatus_th(claim_obj.get(0).getScmaOidClStatus());
                List<ClLine> cline_obj = (List<ClLine>) cl_line_repository.get_CL_Line(claim_obj.get(0).getClamOid());          
                if (cline_obj.size() >= 1) {
                    LocalDate min_incur_date_from = cline_obj.get(0).getIncurDateFrom();
                    for (int j = 0 ; j < cline_obj.size() ; j++)
                    {
                        if (cline_obj.get(j).getIncurDateFrom().compareTo(min_incur_date_from) < 0)
                        {
                            min_incur_date_from = cline_obj.get(j).getIncurDateFrom();
                        }
                    }
                    claim_info_obj.setStart(min_incur_date_from.toString());
                    LocalDate max_incur_date_to = cline_obj.get(0).getIncurDateTo();
                    for (int j = 0 ; j < cline_obj.size() ; j++)
                    {
                        if (cline_obj.get(j).getIncurDateTo().compareTo(max_incur_date_to) > 0)
                        {
                            max_incur_date_to = cline_obj.get(j).getIncurDateTo();
                        }
                    }
                    claim_info_obj.setFinish(max_incur_date_to.toString());
                    claim_info_obj.setClaim_type(cline_obj.get(0).getScmaOidClType());
                    BigDecimal result = BigDecimal.valueOf(0);
                    for (int j = 0 ; j < cline_obj.size() ; j++)
                    {
                        result = result.add(cline_obj.get(j).getPresAmt());
                    }
                    claim_info_obj.setBilled(result);
                    result = BigDecimal.valueOf(0);
                    for (int j = 0 ; j < cline_obj.size() ; j++)
                    {
                        result = result.add(cline_obj.get(j).getAppAmt());
                    }
                    claim_info_obj.setAccepted(result);
                    result = BigDecimal.valueOf(0);
                    for (int j = 0 ; j < cline_obj.size() ; j++)
                    {
                        result = result.add(cline_obj.get(j).getPresAmt().subtract(cline_obj.get(j).getAppAmt()));
                    }
                    claim_info_obj.setUnpaid(result);
                    claim_info_obj.setCash_member(cline_obj.get(0).getPriorPaid());
                    claim_info_obj.setTotal_paid(cline_obj.get(0).getPayAmt());
                    claim_info_obj.setCoverage(cline_obj.get(0).getScmaOidBedType());

                    if (cline_obj.get(0).getChqDate()==null)
                    {
                        claim_info_obj.setPayment_date("");
                    }
                    else
                    {
                     claim_info_obj.setPayment_date(cline_obj.get(0).getChqDate().toString());
                    }
                    claim_info_obj.setStatus_code(cline_obj.get(0).getScmaOidClLineStatus());
                    claim_info_obj.setPay_to(cline_obj.get(0).getPayee());
                } else {
                    claim_info_list.add(claim_info_obj);
                    return claim_info_list;
                } 
            } else {
                claim_info_list.add(claim_info_obj);
                return claim_info_list;
            }
            claim_info_list.add(claim_info_obj);
        }
        return claim_info_list;
    }

    @PostMapping(path="/inquiry_claim_detail")
    public claim_info inquiry_claim_detail(@RequestParam Map<String, String> requestParams) {

        String CLAIM_NO = requestParams.get("CLAIM_NO");
        claim_info claim_info_obj = new claim_info();

        List<ClClaim> claim_obj = (List<ClClaim>) cl_claim_repository.get_CL_CLaim(CLAIM_NO);
        if (claim_obj.size() >= 1) {
            claim_info_obj.setClaim_id(claim_obj.get(0).getClamOid());
            claim_info_obj.setClaim_no(claim_obj.get(0).getClNo());
            claim_info_obj.setStatus(claim_obj.get(0).getScmaOidClStatus());
            claim_info_obj.setStatus_th(claim_obj.get(0).getScmaOidClStatus());
            List<ClLine> cline_obj = (List<ClLine>) cl_line_repository.get_CL_Line(claim_obj.get(0).getClamOid());
            if (cline_obj.size() >= 1)
            {
                claim_info_obj.setStart(cline_obj.get(0).getIncurDateFrom().toString());
                claim_info_obj.setFinish(cline_obj.get(0).getIncurDateTo().toString());
                claim_info_obj.setClaim_type(cline_obj.get(0).getScmaOidClType());
                claim_info_obj.setBilled(cline_obj.get(0).getPresAmt());
                claim_info_obj.setAccepted(cline_obj.get(0).getAppAmt());
                claim_info_obj.setUnpaid(claim_info_obj.getBilled().subtract(claim_info_obj.getAccepted()));
                claim_info_obj.setCash_member(cline_obj.get(0).getPriorPaid());
                claim_info_obj.setTotal_paid(cline_obj.get(0).getPayAmt());
                claim_info_obj.setCoverage(cline_obj.get(0).getScmaOidBedType());
                claim_info_obj.setPayment_date(cline_obj.get(0).getPayDate().toString());
                claim_info_obj.setStatus_code(cline_obj.get(0).getScmaOidClLineStatus());
                claim_info_obj.setPay_to(cline_obj.get(0).getPayee());
                claim_info_obj.setBenefit_description(cline_obj.get(0).getScmaOidBedType());
            }
            else
            {
                return claim_info_obj;
            }
        }
        else
        {
            return claim_info_obj;
        }
        return claim_info_obj;
    }


     @PostMapping(path="/inquiry_claim_detail2")
    //public List<claim_info> inquiry_claim_detail2(@RequestParam Map<String, String> requestParams) {
    public List<inquiry_claim_detail> inquiry_claim_detail2(@RequestParam Map<String, String> requestParams) {
        //List<ClClaim> claim_obj = (List<ClClaim>) cl_claim_repository.get_CL_Line_From_Memb();
        String MBR_NO = requestParams.get("MBR_NO");
        //List<MrMember> member_obj = (List<MrMember>) mr_member_repository.get_MEMB_OID_From_MBR_NO(MBR_NO);
        List<MrMember> member_obj = (List<MrMember>) mr_member_repository.get_MEMB_OID_From_MBR_NO(MBR_NO);
        List<ClLine> cline_objs = (List<ClLine>) cl_line_repository.get_CL_Line_From_Memb(member_obj.get(0).getMemb_oid());
        //List<claim_info> claim_info_list = new ArrayList<>();
        List<inquiry_claim_detail> claim_info_list = new ArrayList<>();
        for (int i = 0 ; i < cline_objs.size() ; i++)
        {
            //sql_obj.setSQL1(list_obj.get(i).getSQL1());
            //retval.add(sql_obj);
            //String CLAIM_NO = requestParams.get("CLAIM_NO");
            //cline_objs.get(i).getClamOid();

            //claim_info claim_info_obj = new claim_info();
            inquiry_claim_detail claim_info_obj = new inquiry_claim_detail();

            //List<claim_info> claim_info_list = new ArrayList<>();
            //List<ClClaim> claim_obj = (List<ClClaim>) cl_claim_repository.get_CL_CLaim(CLAIM_NO);
            List<ClClaim> claim_obj = (List<ClClaim>) cl_claim_repository.get_CL_CLaim_From_CLAIM_OID(cline_objs.get(i).getClamOid());

            if (claim_obj.size() >= 1)
            {
                claim_info_obj.setClaim_id(claim_obj.get(0).getClamOid());
                claim_info_obj.setClaim_id(claim_obj.get(0).getClamOid());
                claim_info_obj.setClaim_no(claim_obj.get(0).getClNo());
                //claim_info_obj.setStatus(claim_obj.get(0).getScmaOidClStatus());
                //claim_info_obj.setStatus_th(claim_obj.get(0).getScmaOidClStatus());
                List<ClLine> cline_obj = (List<ClLine>) cl_line_repository.get_CL_Line(claim_obj.get(0).getClamOid());
                if (cline_obj.size() >= 1) {

                    LocalDate min_incur_date_from = cline_obj.get(0).getIncurDateFrom();
                    for (int j = 0 ; j < cline_obj.size() ; j++)
                    {
                        if (cline_obj.get(j).getIncurDateFrom().compareTo(min_incur_date_from) < 0)
                        {
                            min_incur_date_from = cline_obj.get(j).getIncurDateFrom();
                        }
                    }

                    //claim_info_obj.setStart(cline_obj.get(0).getIncurDateFrom().toString());
                    //claim_info_obj.setStart(min_incur_date_from.toString());

                    LocalDate max_incur_date_to = cline_obj.get(0).getIncurDateTo();
                    for (int j = 0 ; j < cline_obj.size() ; j++)
                    {
                        if (cline_obj.get(j).getIncurDateTo().compareTo(max_incur_date_to) > 0)
                        {
                            max_incur_date_to = cline_obj.get(j).getIncurDateTo();
                        }
                    }
                    //claim_info_obj.setFinish(cline_obj.get(0).getIncurDateTo().toString());
                    //claim_info_obj.setFinish(max_incur_date_to.toString());

                    //claim_info_obj.setClaim_type(cline_obj.get(0).getScmaOidClType());

                    BigDecimal result = BigDecimal.valueOf(0);
                    for (int j = 0 ; j < cline_obj.size() ; j++)
                    {
                        result = result.add(cline_obj.get(j).getPresAmt());
                    }

                    claim_info_obj.setBilled(result);

                    result = BigDecimal.valueOf(0);
                    for (int j = 0 ; j < cline_obj.size() ; j++)
                    {
                        result = result.add(cline_obj.get(j).getAppAmt());
                    }

                    claim_info_obj.setAccepted(result);

                    result = BigDecimal.valueOf(0);
                    for (int j = 0 ; j < cline_obj.size() ; j++)
                    {
                        result = result.add(cline_obj.get(j).getPresAmt().subtract(cline_obj.get(j).getAppAmt()));
                    }
                    claim_info_obj.setUnpaid(result);

                    claim_info_obj.setCash_member(cline_obj.get(0).getPriorPaid());
                    //claim_info_obj.setTotal_paid(cline_obj.get(0).getPayAmt());
                    //claim_info_obj.setCoverage(cline_obj.get(0).getScmaOidBedType());
                    if (cline_obj.get(0).getChqDate()==null)
                    {
                        //claim_info_obj.setPayment_date("");
                    }
                    else
                    {
                        //claim_info_obj.setPayment_date(cline_obj.get(0).getPayDate().toString());
                        //claim_info_obj.setPayment_date(cline_obj.get(0).getChqDate().toString());
                    }
                    //claim_info_obj.setStatus_code(cline_obj.get(0).getScmaOidClLineStatus());
                    //claim_info_obj.setPay_to(cline_obj.get(0).getPayee());
                    claim_info_obj.setBenefit_description(cline_obj.get(0).getScmaOidBedType());
                } else {
                    claim_info_list.add(claim_info_obj);
                    return claim_info_list;
                }
            } else
            {
                claim_info_list.add(claim_info_obj);
                return claim_info_list;
            }
            claim_info_list.add(claim_info_obj);
        }
        return claim_info_list;
    }










}







