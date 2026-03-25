package com.gemengserv.bff.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gemengserv.bff.service.GemQcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gemqc")
public class GemQcController
{
    @Autowired
    private GemQcService gemQcService;

    // Activity Inspection Controller

    @RequestMapping(value = "/rest/v1/activity/inspection", method = RequestMethod.GET)
    public ResponseEntity<Object> getActivityFirstTimeRight(@RequestParam("user_id") Integer userId,
                                                            @RequestParam(value = "project_id", required = false) Integer projectId,
                                                            @RequestParam(value = "from_date", required = false) String fromDate,
                                                            @RequestParam(value = "to_date", required = false) String toDate,
                                                            @RequestParam("token") String token,
                                                            @RequestParam(value = "page_size", defaultValue = "1000", required = false) Integer pageSize,
                                                            @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page)
    {
        return gemQcService.getActivityFirstTimeRight(userId, projectId, fromDate, toDate, token, pageSize, page);
    }

    @RequestMapping(value = "/rest/v1/activity/getFloorWiseRFIDetails", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getFloorWiseRFIDetails(@RequestParam(value = "user_id") int userId,
                                                                      @RequestParam(value = "token") String token,
                                                                      @RequestParam(value = "projectId",required = false) int projectId)
    {
        return gemQcService.getFloorWiseRFIDetails(userId, token, projectId);
    }


    // Activity Master Controller

    @RequestMapping(value = "/rest/v1/activity/master", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getActivitiesByUser(
            @RequestParam("user_id") int userId,
            @RequestParam("project_id") int projectId,
            @RequestParam("token") String token,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return gemQcService.getActivitiesByUser(userId,projectId,token,pageSize,page,lastSync);
    }

    @RequestMapping(value = "/rest/v1/activity/checklist/master", method = RequestMethod.GET)
    public ResponseEntity<LinkedHashMap<String, Object>> getChecklists(
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam("user_id") int user_id, @RequestParam("project_id") int project_id,
            @RequestParam("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return gemQcService.getChecklists(page,pageSize,user_id,project_id,token,lastSync);
    }

    @RequestMapping(value = "/activity/master/{pid}", method = RequestMethod.GET)
    public ModelAndView activityMaster(@PathVariable("pid") int pid)
    {
        return gemQcService.activityMaster(pid);
    }

    @RequestMapping(value = "/activity/master/find", method = RequestMethod.GET)
    public Object getActivityChecklistMaster()
    {
        return gemQcService.getActivityChecklistMaster();
    }

    @RequestMapping(value = "/activity/create", method = RequestMethod.POST)
    public Object postActivityData(@RequestBody String data)
    {
        return gemQcService.postActivityData(data);
    }

    @RequestMapping(value = "/activity/checklist/update", method = RequestMethod.POST)
    public Object addCheckListQuestions(@RequestBody String data)
    {
        return gemQcService.addCheckListQuestions(data);
    }

    // CRFI Controller

    @Deprecated
    @RequestMapping(value = "/rest/v1/activity/request/findall", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllActivityRequestsByProjectId(@RequestParam("pid") long pid) {
        return gemQcService.getAllActivityRequestsByProjectId(pid);
    }

    @RequestMapping(value = "/rest/v1/activity/request/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAllActivityRequestsByFilter(
            @RequestBody Map<String, Object> requestParam)
    {
        return gemQcService.getAllActivityRequestsByFilter(requestParam);
    }

    @RequestMapping(value = "/rest/v1/activity/request/find_sp", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAllActivityRequestsBySP(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.getAllActivityRequestsBySP(requestParam);
    }

//    @RequestMapping(value = "/rest/v1/activity/request/find", method = RequestMethod.POST)
//    public ResponseEntity<Map<String, Object>> getAllActivityRequestBySP(@RequestBody Map<String, Object> requestParam) {
//        return gemQcService.getAllActivityRequestBySP(requestParam);
//    }

    @RequestMapping(value = "/rest/v1/activity/request/findHistory_Old", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAllActivityRequestHistoryByIds(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.getAllActivityRequestHistoryByIds(requestParam);
    }

    @RequestMapping(value = "/rest/v1/activity/request/findHistory", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getRFHistorybySP(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.getRFHistorybySP(requestParam);
    }

    @RequestMapping(value = "/rest/v1/activity/request/createorupdate", method = RequestMethod.POST)
    public synchronized ResponseEntity<Map<String, Object>> createActivityRequest(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.createActivityRequest(requestParam);
    }

    @RequestMapping(value = "/rest/v1/activity/request/checklist/create", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createActivityChecklist(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.createActivityChecklist(requestParam);
    }

    @Deprecated
    @RequestMapping(value = "/rest/v1/activity/request/checklist/update", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateActivityChecklist(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.updateActivityChecklist(requestParam);
    }

    @RequestMapping(value = "/rest/v1/activity/request/checklist/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getActivityChecklist(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.getActivityChecklist(requestParam);
    }

    @RequestMapping(value = "/rest/v1/activity/request/getCRFIClosureReportData", method = RequestMethod.GET)
    public ResponseEntity<Object> getCRFIClosureReportData(@RequestParam("crfiId") Integer crfiId) {
        return gemQcService.getCRFIClosureReportData(crfiId);
    }

    @RequestMapping(value = "/rest/v1/activity/request/generateCRFIClosureReportByCrfiId", method = RequestMethod.GET)
    public ResponseEntity<Object> generateCRFIClosureReportByCrfiId(@RequestHeader(value = "userId") Integer userId,
                                                                    @RequestHeader(value = "token") String token,
                                                                    @RequestParam("crfiId") Integer crfiId) {
        return gemQcService.generateCRFIClosureReportByCrfiId(userId, token, crfiId);
    }

    @RequestMapping(value = "/rest/v1/activity/request/checkDependantActivites", method = RequestMethod.GET)
    public ResponseEntity<Object> checkDependantActivites(@RequestHeader(value = "userId") Integer userId,
                                                          @RequestHeader(value = "token") String token,
                                                          @RequestParam("locationId") Integer locationId,
                                                          @RequestParam("activityId") Integer activityId,
                                                          @RequestParam("projectId") Integer projectId) {
        return gemQcService.checkDependantActivites(userId, token, locationId, activityId, projectId);
    }

    @RequestMapping(value = "/rest/v1/activity/request/generateCRFIClosureExcelReportByCrfiId", method = RequestMethod.GET)
    public ResponseEntity<Object> generateCRFIClosureExcelReportByCrfiId(@RequestHeader(value = "userId") Integer userId,
                                                                         @RequestHeader(value = "token") String token,
                                                                         @RequestParam("crfiId") Integer crfiId) {
        return gemQcService.generateCRFIClosureExcelReportByCrfiId(userId, token, crfiId);
    }

    @RequestMapping(value = "/rest/v1/activity/request/instruction", method = RequestMethod.POST)
    public synchronized ResponseEntity<Map<String, Object>> createInstructCrfi(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.createInstructCrfi(requestParam);
    }

    // Activity Type Of Work Mapping Controller

    @GetMapping(value = "/rest/v1/activityTypeOfWork", produces = "application/json")
    ResponseEntity<Object> getActivityTypeOfWorkMapping(@RequestHeader("user_id") int userId,
                                                        @RequestHeader("token") String token,
                                                        @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return gemQcService.getActivityTypeOfWorkMapping(userId,token,lastSync);
    }

    // Activity Unit Mapping Controller

    @GetMapping(value = "activityUnitMapping")
    List<Object> getAllActivityUnitMapping()
    {
        return gemQcService.getAllActivityUnitMapping();
    }

    // Common Controller

    @RequestMapping(value = "/rest/v1/configuration", method = RequestMethod.GET)
    ResponseEntity<Object> getConfiguration(@RequestParam("package_id") String package_id)
    {
        return gemQcService.getConfiguration(package_id);
    }

    @RequestMapping(value = "/rest/v1/login", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username,
                                              @RequestParam("password") String password) throws Exception
    {
        return gemQcService.login(username, password);
    }

    @RequestMapping(value = "/rest/v1/checkotp", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> checkotp(@RequestParam("otp") int otp,
                                                 @RequestParam("user_id") int user_id,
                                                 @RequestParam("version") String version,
                                                 @RequestParam("device_code") String device_code)
    {
        return gemQcService.checkotp(otp, user_id, version, device_code);
    }

    @RequestMapping(value = "/rest/v1/logout", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> logout(@RequestParam("user_id") int user_id,
                                               @RequestParam("version") String version,
                                               @RequestParam("device_code") String device_code)
    {
        return gemQcService.logout(user_id, version, device_code);
    }

    @RequestMapping(value = "/rest/v1/uploadMultipleFiles", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadMultipleFileHandler(@RequestParam("user_id") int userId,
                                                            @RequestParam("token") String token,
                                                            @RequestParam("event_id") int eventId,
                                                            @RequestParam("event_name") String eventName,
                                                            @RequestParam("file") MultipartFile[] files,
                                                            @RequestParam(value = "comment", required = false, defaultValue = "--") String comment)
    {
        return gemQcService.uploadMultipleFileHandler(userId, token, eventId, eventName, files, comment);
    }

    @RequestMapping(value = "/rest/v1/userDetails", method = RequestMethod.GET)
    ResponseEntity<Object> userDetails(@RequestParam("user_id") int user_id,
                                  @RequestParam("token") String token)
    {
        return gemQcService.userDetails(user_id, token);
    }

    @RequestMapping(value = "/rest/v1/media/find", method = RequestMethod.GET)
    void doDownload(@RequestParam("media_url") String mediaUrl,
                    @RequestParam("user_id") int user_id,
                    @RequestParam("token") String token,
                    HttpServletResponse response) throws IOException
    {
        gemQcService.doDownload(mediaUrl, user_id, token, response);
    }

    @RequestMapping(value = "/rest/v1/addDeviceInfo", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addDeviceInfo(@RequestBody Map<String, Object> requestParam)
    {
        return gemQcService.addDeviceInfo(requestParam);
    }

    @RequestMapping(value = "/rest/v1/notification/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findUnseensNotifications(@RequestParam("user_id") int userId, @RequestParam("token") String token,
                                                                        @RequestParam("project_id") int projectId)
    {
        return gemQcService.findUnseensNotifications(userId, token, projectId);
    }

    @RequestMapping(value = "/rest/v1/common/heartbeat", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> heartBeat(@RequestParam("user_id") int userId) {
        return gemQcService.heartBeat(userId);
    }

    @RequestMapping(value = "/rest/v1/common/usertrack/update", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> updateUserTrack(@RequestParam("user_id") List<Integer> userIds) {
        return gemQcService.updateUserTrack(userIds);
    }

    @RequestMapping(value = "/rest/v1/common/updateRFISequence", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> updateSequenceCode() {
        return gemQcService.updateSequenceCode();
    }

    @RequestMapping(value = "/rest/v1/common/updateSyncPendingStatus", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> updateSyncPendingStatus() {
        return gemQcService.updateSyncPendingStatus();
    }

    @RequestMapping(value = "/rest/v1/upload/signature", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadSignature(@RequestParam("user_id") int user_id, @RequestParam("token") String token, @RequestParam(value = "file") MultipartFile file) {
        return gemQcService.uploadSignature(user_id, token, file);
    }

    @RequestMapping(value = "/rest/v1/common/getUnitMaster", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getUnitMaster(@RequestParam("user_id") int userId, @RequestParam("token") String token) {
        return gemQcService.getUnitMaster(userId, token);
    }

    @RequestMapping(value = "/rest/v1/loginAD", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> loginAD(@RequestParam("username") String username, @RequestParam("password") String password) {
        return gemQcService.loginAD(username, password);
    }

    @RequestMapping(value = "/rest/v1/findCompanies", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findCompanies(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token) {
        return gemQcService.findCompanies(userId, token);
    }

    @RequestMapping(value = "/rest/v1/common/masterlist/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getMasterList(@RequestBody Map<String, Object> requestParam)
    {
        return gemQcService.getMasterList(requestParam);
    }

    @PostMapping(value = "/rest/v1/upload/signature", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadSignature(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestParam(value = "file") MultipartFile file)
    {
        return gemQcService.uploadSignature(userId, token, file);
    }

    @PostMapping("/rest/v2/login")
    ResponseEntity<Map<String, Object>> loginAPI(@RequestParam("username") String username,
                               @RequestParam("password") String password)
    {
        return gemQcService.loginAPI(username, password);
    }

    @PostMapping("/rest/v1/users/userinfo")
    ResponseEntity<Object> addDeviceTokenAndAppVersion(@RequestHeader("user_id") int userId,
                                                  @RequestHeader("token") String token,
                                                  @RequestParam("deviceToken") String deviceToken,
                                                  @RequestParam(value = "appVersion", defaultValue = "1", required = false) String appVersion)
    {
        return gemQcService.addDeviceTokenAndAppVersion(userId, token, deviceToken, appVersion);
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Object> registerUser(@RequestBody Object userRegisterRequest)
    {
        return gemQcService.registerUser(userRegisterRequest);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = {"application/json"})
    ResponseEntity<Object> registerUser(@RequestParam(value = "user_id") Integer userId)
    {
        return gemQcService.registerUser(userId);
    }

    // Company Activity Controller

    @RequestMapping(value = "/rest/v1/getAllCompanyActivity_old", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getCompanyActivity(@RequestParam("user_id") int userId,
                                                     @RequestParam("token") String token,
                                                     @RequestParam(value = "project_id", defaultValue = "0", required = false) int projectId,
                                                     @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                     @RequestParam(value = "page_num", defaultValue = "1", required = false) int page) {
        return gemQcService.getCompanyActivity(userId, token, projectId, pageSize, page);
    }

    @RequestMapping(value = "/rest/v1/getAllCompanyActivity", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getCompanyActivitySP(@RequestParam("user_id") int userId,
                                                       @RequestParam("token") String token,
                                                       @RequestParam(value = "project_id", defaultValue = "0", required = false) int projectId,
                                                       @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                       @RequestParam(value = "page_num", defaultValue = "1", required = false) int page) {
        return gemQcService.getCompanyActivitySP(userId, token, projectId, pageSize, page);
    }

    @RequestMapping(value = "/rest/v2/getAllCompanyActivity", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getCompanyActivityV2(@RequestParam("user_id") int userId,
                                                       @RequestParam("token") String token,
                                                       @RequestParam(value = "project_id", defaultValue = "0", required = false) int projectId,
                                                       @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                       @RequestParam(value = "page_num", defaultValue = "1", required = false) int page) {
        return gemQcService.getCompanyActivityV2(userId, token, projectId, pageSize, page);
    }

    // Cube Test Alone Controller

    @RequestMapping(value = "/rest/v1/cubeTestA/create", method = RequestMethod.POST, produces = { "application/json" })
    public ResponseEntity<Object> createCubeTestA(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestBody Object cubeTestARequest) {
        return gemQcService.createCubeTestA(userId, token, cubeTestARequest);
    }

    @RequestMapping(value = "/rest/v1/cubeTestA/update", method = RequestMethod.PUT, produces = { "application/json" })
    public ResponseEntity<Object> updateCubeTestA(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestBody Object cubeTestARequest) {
        return gemQcService.updateCubeTestA(userId, token, cubeTestARequest);
    }

    @RequestMapping(value = "/rest/v1/cubeTestA/find", method = RequestMethod.GET, produces = { "application/json" })
    public ResponseEntity<Object> fetchCubeTestA(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestParam(value = "project_id") int projectId, @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum, @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize) {
        return gemQcService.fetchCubeTestA(userId, token, projectId, pageNum, pageSize);
    }

    @RequestMapping(value = "/rest/v1/cubeTestA/find/cubeTestById", method = RequestMethod.GET, produces = { "application/json" })
    public ResponseEntity<Map<String, Object>> fetchCubeTestAById(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestParam(value = "id") int cubeTestAById) {
        return gemQcService.fetchCubeTestAById(userId, token, cubeTestAById);
    }

    // Cube Test Controller

    @RequestMapping(value = "/rest/v1/cubeTest/main/create", method = RequestMethod.POST, produces = { "application/json" })
    public ResponseEntity<Object> createCubeTest(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestBody Object cubeTestRequest) {
        return gemQcService.createCubeTest(userId, token, cubeTestRequest);
    }

    @RequestMapping(value = "/rest/v1/cubeTest/update", method = RequestMethod.PUT, produces = { "application/json" })
    public ResponseEntity<Object> updateCubeTest(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestBody Object cubeTestRequest) {
        return gemQcService.updateCubeTest(userId, token, cubeTestRequest);
    }

    @RequestMapping(value = "/rest/v1/cubeTest/updateCubeTest", method = RequestMethod.PUT, produces = { "application/json" })
    public ResponseEntity<Object> updateCubeTestObj(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestBody List<Object> updateCubeTestModel) {
        return gemQcService.updateCubeTestObj(userId, token, updateCubeTestModel);
    }

    @RequestMapping(value = "/rest/v1/cubeTest/main/find", method = RequestMethod.GET, produces = { "application/json" })
    public ResponseEntity<Object> fetchCubeTest(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestParam(value = "project_id") int projectId, @RequestParam(value = "crfi_id", defaultValue = "0", required = false) int crfi_id, @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum, @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize) {
        return gemQcService.fetchCubeTest(userId, token, projectId, crfi_id, pageNum, pageSize);
    }

    @RequestMapping(value = "/rest/v1/cubeTest/find/cubeTestById", method = RequestMethod.GET, produces = { "application/json" })
    public ResponseEntity<Map<String, Object>> fetchCubeTestById(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestParam(value = "id") int cubeTestId) {
        return gemQcService.fetchCubeTestById(userId, token, cubeTestId);
    }

    // Dashboard Controller

    @RequestMapping(value = "/rest/v1/activity/firstTimeRight", method = RequestMethod.GET)
    public ResponseEntity<Object> getFirstTimeRight(@RequestParam(value = "user_id", required = false) Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam(value = "from_date", required = false) String fromDate, @RequestParam(value = "to_date", required = false) String toDate, @RequestParam(value = "token", required = false) String token) {
        return gemQcService.getFirstTimeRight(userId, projectId, fromDate, toDate, token);
    }

    @RequestMapping(value = "/rest/v1/activity/rejections", method = RequestMethod.GET)
    public ResponseEntity<Object> getRejections(@RequestParam(value = "user_id", required = false) Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam(value = "from_date", required = false) String fromDate, @RequestParam(value = "to_date", required = false) String toDate, @RequestParam(value = "token", required = false) String token) {
        return gemQcService.getRejections(userId, projectId, fromDate, toDate, token);
    }

    @RequestMapping(value = "/rest/v1/activity/comparison", method = RequestMethod.GET)
    public ResponseEntity<Object> getComparison(@RequestParam(value = "user_id", required = false) Integer userId, @RequestParam("module") String module, @RequestParam("status") String status, @RequestParam(value = "project_id", required = false) Integer projectId, @RequestParam(value = "activity_id", required = false) Integer activityId, @RequestParam(value = "company_id", required = false) Integer companyId, @RequestParam(value = "from_date", required = false) String fromDate, @RequestParam(value = "to_date", required = false) String toDate, @RequestParam(value = "token", required = false) String token) {
        return gemQcService.getComparison(userId, module, status, projectId, activityId, companyId, fromDate, toDate, token);
    }

    @RequestMapping(value = "/rest/v1/activity/turnAroundTime", method = RequestMethod.GET)
    public ResponseEntity<Object> getTurnAroundTime(@RequestParam(value = "user_id", required = false) Integer userId, @RequestParam("module") String module, @RequestParam("status") String status, @RequestParam(value = "location_id", required = false) Integer locationId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam(value = "from_date", required = false) String fromDate, @RequestParam(value = "to_date", required = false) String toDate, @RequestParam(value = "token", required = false) String token) {
        return gemQcService.getTurnAroundTime(userId, module, status, locationId, projectId, activityId, fromDate, toDate, token);
    }

    @RequestMapping(value = "/rest/v1/activity/ftr/lastSixMonths", method = RequestMethod.GET)
    public ResponseEntity<Object> getLastSixMonthsFTR(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("duration") String duration, @RequestParam("token") String token) {
        return gemQcService.getLastSixMonthsFTR(userId, projectId, duration, token);
    }

    @RequestMapping(value = "/rest/v1/activity/ftr/lastSevenDays", method = RequestMethod.GET)
    public ResponseEntity<Object> getLastSevenDaysFTR(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("duration") String duration, @RequestParam("token") String token) {
        return gemQcService.getLastSevenDaysFTR(userId, projectId, duration, token);
    }

    @RequestMapping(value = "/rest/v1/activity/ftr/month", method = RequestMethod.GET)
    public ResponseEntity<Object> getActivityFTRByMonth(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("year") Integer year, @RequestParam("month") String month, @RequestParam("token") String token) {
        return gemQcService.getActivityFTRByMonth(userId, projectId, year, month, token);
    }

    @RequestMapping(value = "/rest/v1/activity/ftr/date", method = RequestMethod.GET)
    public ResponseEntity<Object> getActivityFTRByDate(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("date") String date, @RequestParam("token") String token) {
        return gemQcService.getActivityFTRByDate(userId, projectId, date, token);
    }

    @RequestMapping(value = "/rest/v1/activity/ftr/dateRange", method = RequestMethod.GET)
    public ResponseEntity<Object> getFTRByDateRange(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("token") String token) {
        return gemQcService.getFTRByDateRange(userId, projectId, startDate, endDate, token);
    }

    @RequestMapping(value = "/rest/v1/activity/rejection/lastSixMonths", method = RequestMethod.GET)
    public ResponseEntity<Object> getLastSixMonthsRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("duration") String duration, @RequestParam("token") String token) {
        return gemQcService.getLastSixMonthsRejection(userId, projectId, duration, token);
    }

    @RequestMapping(value = "/rest/v1/activity/rejection/lastSevenDays", method = RequestMethod.GET)
    public ResponseEntity<Object> getLastSevenDaysRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("duration") String duration, @RequestParam("token") String token) {
        return gemQcService.getLastSevenDaysRejection(userId, projectId, duration, token);
    }

    @RequestMapping(value = "/rest/v1/activity/rejection/dateRange", method = RequestMethod.GET)
    public ResponseEntity<Object> getRejectionByDateRange(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("token") String token) {
        return gemQcService.getRejectionByDateRange(userId, projectId, startDate, endDate, token);
    }

    @RequestMapping(value = "/rest/v1/activity/rejection/tower/lastSixMonths", method = RequestMethod.GET)
    public ResponseEntity<Object> getLastSixMonthsTowerRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam(value = "tower_id", required = false) Integer towerId, @RequestParam("duration") String duration, @RequestParam("token") String token) {
        return gemQcService.getLastSixMonthsTowerRejection(userId, projectId, activityId, towerId, duration, token);
    }

    @RequestMapping(value = "/rest/v1/activity/rejection/tower/lastSevenDays", method = RequestMethod.GET)
    public ResponseEntity<Object> getLastSevenDaysTowerRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam(value = "tower_id", required = false) Integer towerId, @RequestParam("duration") String duration, @RequestParam("token") String token) {
        return gemQcService.getLastSevenDaysTowerRejection(userId, projectId, activityId, towerId, duration, token);
    }

    @RequestMapping(value = "/rest/v1/activity/rejection/tower/dateRange", method = RequestMethod.GET)
    public ResponseEntity<Object> getTowerRejectionByDateRange(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam(value = "tower_id", required = false) Integer towerId, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("token") String token) {
        return gemQcService.getTowerRejectionByDateRange(userId, projectId, activityId, towerId, startDate, endDate, token);
    }

    @RequestMapping(value = "/rest/v1/project/activity/ftr/lastSixMonths", method = RequestMethod.GET)
    public ResponseEntity<Object> getLastSixMonthsActivityFTR(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("duration") String duration, @RequestParam("token") String token) {
        return gemQcService.getLastSixMonthsActivityFTR(userId, projectId, activityId, duration, token);
    }

    @RequestMapping(value = "/rest/v1/project/activity/ftr/lastSevenDays", method = RequestMethod.GET)
    public ResponseEntity<Object> getLastSevenDaysActivityFTR(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("duration") String duration, @RequestParam("token") String token) {
        return gemQcService.getLastSevenDaysActivityFTR(userId, projectId, activityId, duration, token);
    }

    @RequestMapping(value = "/rest/v1/project/activity/ftr/dateRange", method = RequestMethod.GET)
    public ResponseEntity<Object> getActivityFTRByDateRange(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("token") String token) {
        return gemQcService.getActivityFTRByDateRange(userId, projectId, activityId, startDate, endDate, token);
    }

    @RequestMapping(value = "/rest/v1/project/activity/rejection/lastSixMonths", method = RequestMethod.GET)
    public ResponseEntity<Object> getLastSixMonthsActivityRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("duration") String duration, @RequestParam("token") String token) {
        return gemQcService.getLastSixMonthsActivityRejection(userId, projectId, activityId, duration, token);
    }

    @RequestMapping(value = "/rest/v1/project/activity/rejection/lastSevenDays", method = RequestMethod.GET)
    public ResponseEntity<Object> getLastSevenDaysActivityRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("duration") String duration, @RequestParam("token") String token) {
        return gemQcService.getLastSevenDaysActivityRejection(userId, projectId, activityId, duration, token);
    }

    @RequestMapping(value = "/rest/v1/project/activity/rejection/dateRange", method = RequestMethod.GET)
    public ResponseEntity<Object> getActivityRejectionByDateRange(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("token") String token) {
        return gemQcService.getActivityRejectionByDateRange(userId, projectId, activityId, startDate, endDate, token);
    }

    // Location Master Controller

    @RequestMapping(value = "/location/master/", method = RequestMethod.GET)
    public ModelAndView sendredirect() {
        ModelAndView view = new ModelAndView();
        view.setViewName("material_master");
        return view;
    }

    /*@RequestMapping(value = "/location/db/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findLocation(@RequestBody String data) {
        return gemQcService.findLocation(data);
    }*/

   /* @RequestMapping(value = "/location/db/findAll", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findLocationAll(@RequestBody String data) {
        return gemQcService.findLocationAll(data);
    }*/

    @RequestMapping(value = "/location/db/create", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createLocationDB(@RequestBody String data) {
        return gemQcService.createLocationDB(data);
    }

    @RequestMapping(value = "/location/db/reset", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> resetLocationDB() {
        return gemQcService.resetLocationDB();
    }

    // Material Checklist Master Controller

    @RequestMapping(value = "/rest/v1/mrfichecklistmaster" , method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getMRFIMaster(
            @RequestHeader(value = "user_id") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize)
    {
        return gemQcService.getMRFIMaster(userId, token, page, pageSize);
    }

    // Material Inspection Request Controller

    @RequestMapping(value = "/rest/v1/material/inspection/request/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findMaterialInspectionRequest(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.findMaterialInspectionRequest(requestParam);
    }

    @RequestMapping(value = "/rest/v1/material/inspection/request/history/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findMaterialInspectionRequestHistory(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.findMaterialInspectionRequestHistory(requestParam);
    }

    @RequestMapping(value = "/rest/v1/material/inspection/request/createorupdate", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createUpdateMaterialInspectionRequest(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.createUpdateMaterialInspectionRequest(requestParam);
    }

    @RequestMapping(value = "/rest/v1/material/inspection/request/test/create", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createMaterialTestResult(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.createMaterialTestResult(requestParam);
    }

    @RequestMapping(value = "/rest/v1/material/inspection/request/test/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getMaterialTestResult(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.getMaterialTestResult(requestParam);
    }

    @RequestMapping(value = "/rest/v1/material/inspection/request/test/update", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateMaterialTestResult(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.updateMaterialTestResult(requestParam);
    }

    @RequestMapping(value = "/rest/v1/material/inspection/request/checklist/create", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createMrfiChecklist(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.createMrfiChecklist(requestParam);
    }

    // Material Master Controller

    @RequestMapping(value = "/rest/v1/material/master/findall", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllMaterials(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page, @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize, @RequestParam("user_id") int user_id, @RequestParam("token") String token) {
        return gemQcService.getAllMaterials(page, pageSize, user_id, token);
    }

    @RequestMapping(value = "/rest/v1/material/master", method = RequestMethod.GET)
    public ResponseEntity<LinkedHashMap<String, Object>> getMaterials(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page, @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize, @RequestParam(value = "project_id", defaultValue = "0", required = false) int project_id, @RequestParam("user_id") int user_id, @RequestParam("token") String token) {
        return gemQcService.getMaterials(page, pageSize, project_id, user_id, token);
    }

    @RequestMapping(value = "/material/master/{pid}", method = RequestMethod.GET)
    public ModelAndView materialMaster(@PathVariable("pid") int project_id, HttpServletRequest request) {
        return gemQcService.materialMaster(project_id, request);
    }

    @RequestMapping(value = "/material_test/find", method = RequestMethod.GET)
    public Object getAllMaterialTests() {
        return gemQcService.getAllMaterialTests();
    }

    @RequestMapping(value = "/material_details/find", method = RequestMethod.GET)
    public Object getAllMaterialDetails() {
        return gemQcService.getAllMaterialDetails();
    }

    @RequestMapping(value = "/material/create", method = RequestMethod.POST)
    public Object postMaterialData(@RequestBody String data) {
        return gemQcService.postMaterialData(data);
    }

    @RequestMapping(value = "/material/test/update", method = RequestMethod.POST)
    public Object updateMaterialTest(@RequestBody String data) {
        return gemQcService.updateMaterialTest(data);
    }

    @RequestMapping(value = "/rest/v1/vendor/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getVendors(@RequestParam(value = "user_id", required = true) int userId, @RequestParam(value = "token", required = true) String token) {
        return gemQcService.getVendors(userId, token);
    }

    // NCR Controller

    @RequestMapping(value = "/rest/v1/ncr/main/create", method = RequestMethod.POST, produces = { "application/json" })
    public ResponseEntity<Map<String, Object>> createNCRInMainAndHistory(@RequestBody Map<String, Object> data)
    {
        return gemQcService.createNCRInMainAndHistory(data);
    }

    @Deprecated
    @RequestMapping(value = "/rest/v1/ncr/main/findAll", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAllNcrByProjectId(@RequestParam("user_id") int user_id, @RequestParam("token") String token, @RequestParam("project_id") long pid) {
        return gemQcService.getAllNcrByProjectId(user_id, token, pid);
    }

    @RequestMapping(value = "/rest/v1/ncr/history/findAll", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAllNcrHistoryByNcrId(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.getAllNcrHistoryByNcrId(requestParam);
    }

    @RequestMapping(value = "/rest/v1/ncr/main/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAllNcrByFilter(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.getAllNcrByFilter(requestParam);
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/rest/v1/ncr/main/update", method = RequestMethod.POST, produces = { "application/json" })
    public ResponseEntity<Map<String, Object>> updateNcrMain(@RequestBody Map<String, Object> data) {
        return gemQcService.updateNcrMain(data);
    }

    @RequestMapping(value = "/sendNCRNotification", method = RequestMethod.GET)
    public ResponseEntity<Object> sendNCRNotification(@RequestParam(value = "level1LocationId") Integer level1LocationId,
                                                                    @RequestParam(value = "ncrId") Integer obsId,
                                                                    @RequestParam(value = "user_id") Integer userId)
    {
        return gemQcService.sendNCRNotification(level1LocationId,obsId,userId);
    }

    // Observation Master Controller

    @RequestMapping(value = "/rest/v1/observation/master/db/findall", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllObservationsFromDB(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
                                                                        @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                                        HttpServletRequest request,
                                                                        @RequestParam("user_id") int user_id,
                                                                        @RequestParam("token") String token)
    {
        return gemQcService.getAllObservationsFromDB(page, pageSize, request, user_id, token);
    }

    // Observation Request Controller

    @RequestMapping(value = "/rest/v1/observation/request/createorupdate", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createUpdateObservationRequest(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.createUpdateObservationRequest(requestParam);
    }

    @RequestMapping(value = "/rest/v1/observation/request/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findObservationRequest(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.findObservationRequest(requestParam);
    }

    @RequestMapping(value = "/rest/v1/observation/request/history/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findObservationRequestHistory(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.findObservationRequestHistory(requestParam);
    }

    @Deprecated
    @RequestMapping(value = "/rest/v1/observation/request/update", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateObservationRequest(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.updateObservationRequest(requestParam);
    }

    @RequestMapping(value = "/rest/v1/observation/request/sendOBSNotification", method = RequestMethod.GET)
    public ResponseEntity<Object> sendOBSNotification(@RequestParam(value = "level1LocationId") Integer level1LocationId, @RequestParam(value = "obsId") Integer obsId, @RequestParam(value = "user_id") Integer userId)
    {

       return gemQcService.sendOBSNotification(level1LocationId, obsId, userId);
    }

    @RequestMapping(value = "/rest/v1/observation/request/partial", method = RequestMethod.POST)
    public synchronized ResponseEntity<Map<String, Object>> createPartialObs(@RequestBody Map<String, Object> requestParam) {
        return gemQcService.createPartialObs(requestParam);
    }


    // Location Master Controller

   /* @RequestMapping(value = "/location/db/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> findLocation(@RequestBody String data,
                                                     @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return gemQcService.findLocation(data, lastSync);
    }*/

    @RequestMapping(value = "/rest/v1/location/db/findall", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllLocationsFromDB(@RequestParam(value = "project_id", required = true) int pid,
                                                              @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
                                                              @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                              HttpServletRequest request,
                                                              @RequestParam("user_id") int user_id,
                                                              @RequestParam("token") String token)
    {
        return gemQcService.getAllLocationsFromDB(pid,page,pageSize,request,user_id,token);
    }

    // Observation Master Controller

  /*  @RequestMapping(value = "/rest/v1/observation/master/db/findall", method = RequestMethod.GET)
    ResponseEntity<Object> getAllObservationsFromDB(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
                                                    @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                    HttpServletRequest request,
                                                    @RequestHeader(value = "userId") Integer userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return gemQcService.getAllObservationsFromDB(page, pageSize, request, userId, token, lastSync);
    }*/


    // Project Controller

    @RequestMapping(value = "/rest/v1/projects", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> restProjects(@RequestParam("user_id") int user_id,
                                                     @RequestParam("token") String token,
                                                     @RequestParam(value = "lastSync", required = false) String lastSync,
                                                     @RequestParam(value = "zoneId", required = false, defaultValue = "0") int zoneId,
                                                     @RequestParam(value = "fundId", required = false, defaultValue = "0") int fundId) throws JsonParseException, JsonMappingException, IOException
    {
        return gemQcService.restProjects(user_id, token, lastSync, zoneId, fundId);
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    ModelAndView projectById(@RequestParam("pid") int project_id)
    {
        return gemQcService.projectById(project_id);
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public ModelAndView projects(HttpServletRequest request)
    {
        return gemQcService.projects(request);
    }

    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public ModelAndView addProject(@ModelAttribute("project") Object project)
    {
        return gemQcService.addProject(project);
    }

    @RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
    public ModelAndView deleteProject(@RequestParam("pid") int project_id)
    {
        return gemQcService.deleteProject(project_id);
    }

    @RequestMapping(value = "/updateProject", method = RequestMethod.GET)
    public ModelAndView updateProject(@ModelAttribute("project") Object project)
    {
        return gemQcService.updateProject(project);
    }

    // QC TBT Controller

    @PostMapping(value = "/rest/v1/qcTBT")
    public ResponseEntity<Map<String, Object>> saveQcTBT(
            @RequestHeader("user_id") int userId,
            @RequestHeader("token") String token,
            @RequestBody Object qcTBTRequest)
    {
        return gemQcService.saveQcTBT(userId, token, qcTBTRequest);
    }

    @GetMapping(value = "/rest/v1/qcTBT")
    public ResponseEntity<Map<String, Object>> getQcTBTByProjectId(
            @RequestHeader("user_id") int userId,
            @RequestHeader("token") String token,
            @RequestParam("projectId") int projectId)
    {
        return gemQcService.getQcTBTByProjectId(userId, token, projectId);
    }

    @GetMapping(value = "/rest/v1/getQcTbtById/{id}")
    public ResponseEntity<Map<String, Object>> getQcTbtById(
            @RequestHeader("user_id") int userId,
            @RequestHeader("token") String token,
            @PathVariable(value = "id") Integer tbtId)
    {
        return gemQcService.getQcTbtById(userId, token, tbtId);
    }


    // Report Controller

  /*  @RequestMapping(value = "/rest/v1/crfi/report", method = RequestMethod.GET)
//  public String buildCRFIReport(ActivityInspection activityInspection, List<Integer> userIds, ProjectService projectService, UserService userService, CommonService commonService, ActivityMasterService activityMasterService, LocationMasterService locationMasterService, ActivityRequestService activityRequestService, ConfigProperties configProperties) {
    public String buildCRFIReport(Object projectService, Object userService,
                                  Object commonService, Object activityMasterService,
                                  Object locationMasterService, Object activityRequestService,
                                  Object configProperties)

    {
        return gemQcService.buildCRFIReport(projectService, userService, commonService, activityMasterService, locationMasterService, activityRequestService, configProperties);
    }*/

    @RequestMapping(value = "/rest/v1/ncr/report", method = RequestMethod.GET)
    public String buildNCRReport()
    {
        return gemQcService.buildNCRReport();
    }

    @RequestMapping(value = "/rest/v1/obs/report", method = RequestMethod.GET)
    public String buildOBSReport() throws Exception
    {
        return gemQcService.buildOBSReport();
    }

    @RequestMapping(value = "/rest/v1/obs/filter/report", method = RequestMethod.GET)
    public ResponseEntity<Object> safetyObsReportByFilter(@RequestParam(value = "fromDate") String fromDate,
                                                          @RequestParam(value = "toDate") String toDate,
                                                          @RequestParam(value = "userId") Integer userId)
    {
        return gemQcService.safetyObsReportByFilter(fromDate, toDate, userId);
    }

    @RequestMapping(value = "/rest/v1/obs/escalation/report", method = RequestMethod.GET)
    public void escalationOBSReport() throws Exception
    {
        gemQcService.escalationOBSReport();
    }

   /* @RequestMapping(value = "/rest/v1/orl/report", method = RequestMethod.GET)
//  public String ORLMonthlyReport() {
    public String ORLMonthlyReport(Object projectService, Object userService,
                            Object activityRequestService, Object materialInspectionRequestService,
                            Object commonService, Object ncrMainService, Object observationRequestService,
                            Object activityMasterService, Object locationMasterService, Object observationMasterService,
                            Object materialMasterService, Object configProperties, Object commonController)
    {
        return gemQcService.ORLMonthlyReport(projectService, userService, activityRequestService, materialInspectionRequestService, commonService, ncrMainService, observationRequestService, activityMasterService, locationMasterService, observationMasterService, materialMasterService, configProperties, commonController);
    }*/

    @RequestMapping(value = "/rest/v1/crfi/RFIReport", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getDataForRFIReport(@RequestParam(value = "user_id") int userId,
                                                                   @RequestParam(value = "token") String token,
                                                                   @RequestParam(value = "project_id") int projectId,
                                                                   @RequestParam(value = "crfiId",required = false) int crfiId)
    {
        return gemQcService.getDataForRFIReport(userId, token, projectId, crfiId);
    }

    @RequestMapping(value = "/rest/v1/obsreport", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getObsReport(@RequestParam(value = "user_id") int userId,
                                                            @RequestParam(value = "token") String token,
                                                            @RequestParam(value = "obsId",required = false) int obsId)
    {
        return gemQcService.getObsReport(userId, token, obsId);
    }

    @RequestMapping(value = "/rest/v1/downloadNcrPdf", method = RequestMethod.GET)
    public ResponseEntity<Object> downloadNcrPdf(@RequestHeader("user_id") int user_id,
                                            @RequestHeader("token") String token,
                                            @RequestParam(name = "ncrId", required = true, defaultValue = "0") int ncrId)
    {
        return gemQcService.downloadNcrPdf(user_id, token, ncrId);
    }

    @RequestMapping("/rest/v1/downloadOBSPdf")
    public ResponseEntity<Object> downloadObsPdf(@RequestHeader("user_id") int user_id,
                                                 @RequestParam(value = "fromDate") String fromDate,
                                                 @RequestParam(value = "toDate") String toDate,
                                                 @RequestParam(value = "projectId") Integer projectId)
    {
        return gemQcService.downloadObsPdf(user_id, fromDate, toDate, projectId);
    }


        // User Controller

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable("uid") int user_id)
    {
        return gemQcService.getUser(user_id);
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logoutPage (HttpServletRequest request)
    {
        return gemQcService.logoutPage(request);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users()
    {
        return gemQcService.users();
    }

    @RequestMapping(value = "/user/{pid}/{uid}", method = { RequestMethod.GET})
    public ModelAndView userById(@PathVariable("uid") String user_ids,
                                 @PathVariable("pid") int project_id,
                                 HttpServletRequest request)
    {
        return gemQcService.userById(user_ids, project_id, request);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") Object user,
                                @RequestParam("pid") int project_id,
                                @RequestParam("role_id") int role_id)
    {
        return gemQcService.addUser(user, project_id, role_id);
    }

    @RequestMapping(value = "/deleteUser", method = { RequestMethod.GET})
    public ModelAndView deleteUser(@RequestParam("uid") int user_id,
                                   @RequestParam("pid") int project_id,
                                   RedirectAttributes redirectAttributes)
    {
        return gemQcService.deleteUser(user_id, project_id, redirectAttributes);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("user") Object user,
                                   @RequestParam("pid") int project_id)
    {
        return gemQcService.updateUser(user, project_id);
    }

    @RequestMapping(value = "/projectMembers/{id}", method = { RequestMethod.GET})
    public ModelAndView usersByProject(@PathVariable("id") int project_id)
    {
        return gemQcService.usersByProject(project_id);
    }

    @RequestMapping(value = "/updateProjectMembers", method = RequestMethod.POST)
    public ModelAndView updateProjectMembers(@ModelAttribute("project_Members") Object project_Members,
                                              @RequestParam("userIds") String userIds)
    {
        return gemQcService.updateProjectMembers(project_Members, userIds);
    }

    @RequestMapping(value = "/addProjectMembers", method = RequestMethod.POST)
    public ModelAndView addProjectMembers(@RequestParam("user_id") List<Integer> user_id,
                                          @RequestParam("pid") int project_id,
                                          @RequestParam("role_id") int role_id,
                                          HttpServletRequest request)
    {
        return gemQcService.addProjectMembers(user_id, project_id, role_id, request);
    }

    @RequestMapping(value="/checkEmailPhoneNo", method=RequestMethod.GET)
    public String checkEmailPhoneNo(@RequestParam("name") String name,
                                    @RequestParam("value") String value)
    {
        return gemQcService.checkEmailPhoneNo(name, value);
    }

    // Weekly Report Controller

//    @RequestMapping(value = "/rest/v1/weekly/report", method = RequestMethod.GET)
//    public void WeeklyReport(Object projectService, Object activityRequestService,
//                             Object configProperties) throws Exception
//    {
//        gemQcService.WeeklyReport(projectService, activityRequestService, configProperties);
//    }
}
