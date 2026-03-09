package com.gemengserv.bff.controller;

import com.gemengserv.bff.service.QHSE_8081;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tejrajbff")
public class BFF_A1_Controller {

    @Autowired
    QHSE_8081 qhse_8081_Service ;

//    ActivityMasterController

    @RequestMapping(value = "/rest/v1/activity/master", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getActivitiesByUser(
            @RequestParam("user_id") int userId,
            @RequestParam("project_id") int projectId,
            @RequestParam("token") String token,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return qhse_8081_Service.getActivitiesByUser(userId, projectId, token, pageSize, page, lastSync);
    }

    @RequestMapping(value = "/rest/v1/activity/checklist/master", method = RequestMethod.GET)
    public ResponseEntity<LinkedHashMap<String, Object>> getChecklists(
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam("user_id") int user_id, @RequestParam("project_id") int project_id,
            @RequestParam("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return qhse_8081_Service.getChecklists(page, pageSize, user_id, project_id, token, lastSync);
    }

    @GetMapping(value = "/getActivities")
    public ResponseEntity<List<Object>> getActivities()
    {
        return qhse_8081_Service.getActivities();
    }

    @RequestMapping(value = "/addActivities", consumes = "multipart/form-data", method = RequestMethod.POST)
    public String addActivities(@RequestParam(value = "file") MultipartFile file,
                                @RequestParam(value = "userId") int userId)
    {
        return qhse_8081_Service.addActivities(file,userId);
    }

//    ActivityTypeOfWorkMappingController
    @GetMapping(value = "/rest/v1/activityTypeOfWork", produces = "application/json")
    public ResponseEntity<Object> getActivityTypeOfWorkMapping(@RequestHeader("user_id") int userId,
                                                               @RequestHeader("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return qhse_8081_Service.getActivityTypeOfWorkMapping(userId, token, lastSync);
    }

//   ActivityUnitMappingController
    @GetMapping(value = "activityUnitMapping")
    public List<Object> getAllActivityUnitMapping()
    {
        return qhse_8081_Service.getAllActivityUnitMapping();
    }

    //  CommonController
    @RequestMapping(value = "/rest/v1/configuration", method = RequestMethod.GET)
    public ResponseEntity<Object> getConfiguration(@RequestParam("package_id") String package_id) {
        return qhse_8081_Service.getConfiguration(package_id);
    }

    @RequestMapping(value = "/rest/v1/checkotp", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> checkotp(@RequestParam("otp") int otp, @RequestParam("user_id") int user_id, @RequestParam("version") String version,
                                                        @RequestParam("device_code") String device_code) {
        return qhse_8081_Service.checkotp(otp, user_id, version, device_code);
    }

    @RequestMapping(value = "/rest/v1/logout", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> logout(@RequestParam("user_id") int user_id, @RequestParam("version") String version,
                                                      @RequestParam("device_code") String device_code) {
        return qhse_8081_Service.logout(user_id, version, device_code);
    }

    @RequestMapping(value = "/rest/v1/userDetails", method = RequestMethod.GET)
    public ResponseEntity<?> userDetails(@RequestParam("user_id") int user_id,
                                         @RequestParam("token") String token)
    {
        return qhse_8081_Service.userDetails(user_id, token);
    }

    @GetMapping("/company")
    public Object getCombinedResponse(@RequestParam("userId") int userId, @RequestParam("token") String token) {
        return qhse_8081_Service.getCompanies(userId, token);
    }

    @GetMapping("/registerUser")
    public Object registerUser(@RequestBody Object userRegisterRequest) {
        return qhse_8081_Service.registerUser(userRegisterRequest);
    }

    @GetMapping("/rest/v2/login")
    public ResponseEntity<?> loginV2(@RequestParam("username") String username, @RequestParam("password") String password) {
        return qhse_8081_Service.loginAPI(username, password);
    }

    @RequestMapping(value = "/rest/v1/login", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        return qhse_8081_Service.login(username, password);
    }


    // NCR API's

    @RequestMapping(value = "/qc/ncr", method = RequestMethod.POST)
    public ResponseEntity<Object> createQcNcr(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestBody Object ncrRequest)
    {
        return qhse_8081_Service.createQcNcr(userId, token,ncrRequest);
    }

    @RequestMapping(value = "/qc/ncr", method = RequestMethod.GET)
    public ResponseEntity<Object> getNcr( @RequestHeader(value = "userId") Integer userId,
                                     @RequestHeader(value = "token") String token,
                                     @RequestParam(value = "companyId") long companyId,
                                     @RequestParam(value = "projectId") int projectId,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "100") int pageSize)

    {
        return qhse_8081_Service.getNcr(userId, token,companyId,projectId,pageNum,pageSize);
    }

    @RequestMapping(value = "/qc/ncrDetails", method = RequestMethod.GET)
    public ResponseEntity<Object> getNcrDetails( @RequestHeader(value = "userId") Integer userId,
                                     @RequestHeader(value = "token") String token,
                                     @RequestParam(value = "ncrId") long ncrId)

    {
        return qhse_8081_Service.getNcrDetails(userId, token,ncrId);
    }
}
