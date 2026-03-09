package com.gemengserv.bff.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@FeignClient(name = "tejraj", url = "http://localhost:8081")
public interface QHSE_8081 {

    @RequestMapping(value = "/rest/v1/activity/master", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getActivitiesByUser(
            @RequestParam("user_id") int userId,
            @RequestParam("project_id") int projectId,
            @RequestParam("token") String token,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "lastSync", required = false) String lastSync);

    @RequestMapping(value = "/rest/v1/activity/checklist/master", method = RequestMethod.GET)
    public ResponseEntity<LinkedHashMap<String, Object>> getChecklists(
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam("user_id") int user_id, @RequestParam("project_id") int project_id,
            @RequestParam("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync);

    @GetMapping(value = "/getActivities")
    public ResponseEntity<List<Object>> getActivities();

    @RequestMapping(value = "/addActivities", consumes = "multipart/form-data", method = RequestMethod.POST)
    public String addActivities(@RequestParam(value = "file") MultipartFile file,
                                @RequestParam(value = "userId") int userId);

    @GetMapping(value = "/rest/v1/activityTypeOfWork", produces = "application/json")
    public ResponseEntity<Object> getActivityTypeOfWorkMapping(@RequestHeader("user_id") int userId,
                                                               @RequestHeader("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync);


    @GetMapping(value = "activityUnitMapping")
    public List<Object> getAllActivityUnitMapping();

    @GetMapping("/company")
    List<Object> getCompanies( @RequestHeader("userId") int userId,  @RequestHeader("token") String token);

    @PostMapping(value = "/registerUser", produces = "application/json")
    ResponseEntity<Object> registerUser(@RequestBody Object userRegisterRequest);

    @PostMapping("/rest/v2/login")
    public ResponseEntity<?> loginAPI(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping(value = "/rest/v1/login", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping(value = "/rest/v1/configuration", method = RequestMethod.GET)
    public ResponseEntity<Object> getConfiguration(@RequestParam("package_id") String package_id);

    @RequestMapping(value = "/rest/v1/checkotp", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> checkotp(@RequestParam("otp") int otp, @RequestParam("user_id") int user_id, @RequestParam("version") String version,
                                                        @RequestParam("device_code") String device_code);

    @RequestMapping(value = "/rest/v1/logout", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> logout(@RequestParam("user_id") int user_id, @RequestParam("version") String version,
                                                      @RequestParam("device_code") String device_code);


    @RequestMapping(value = "/rest/v1/userDetails", method = RequestMethod.GET)
    public ResponseEntity<?> userDetails(@RequestParam("user_id") int user_id,
                                         @RequestParam("token") String token);


    @RequestMapping(value = "/ncr", method = RequestMethod.POST)
    ResponseEntity<Object> createQcNcr(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ncrRequest);

    @RequestMapping(value = "/qc/ncr", method = RequestMethod.GET)
    ResponseEntity<Object> getNcr(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "companyId") long companyId,
            @RequestParam(value = "projectId") int projectId,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "100") int pageSize);

    @RequestMapping(value = "/qc/ncrDetails", method = RequestMethod.GET)
    ResponseEntity<Object> getNcrDetails(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "ncrId") long ncrId);

    @RequestMapping(value = "/qc/ncr", method = RequestMethod.PUT)
    ResponseEntity<Object> updateNcr(
            @RequestHeader(value = "userId") int userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object qcNcrUpdateRequest);

    @RequestMapping(value = "/qc/filter/ncr", method = RequestMethod.POST)
    ResponseEntity<Object> getNcrFilter(
            @RequestHeader(value = "userId") int userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ncrFilterRequest);

    @RequestMapping(value = "/qc/ncrReport", method = RequestMethod.GET)
    ResponseEntity<?> getDataForNcrReport(
            @RequestParam(value = "user_id") int userId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "ncrId") int ncrId,
            @RequestParam(value = "webCall", defaultValue = "false") boolean webCall);



}
