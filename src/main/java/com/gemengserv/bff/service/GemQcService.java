package com.gemengserv.bff.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gemengserv.bff.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
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

@FeignClient(name = "gemqc", url = "http://localhost:8087", configuration = FeignConfig.class)
public interface GemQcService
{
    // Activity Inspection Controller

    @RequestMapping(value = "/rest/v1/activity/inspection", method = RequestMethod.GET)
    ResponseEntity<Object> getActivityFirstTimeRight(@RequestParam("user_id") Integer userId,
                                                            @RequestParam(value = "project_id", required = false) Integer projectId,
                                                            @RequestParam(value = "from_date", required = false) String fromDate,
                                                            @RequestParam(value = "to_date", required = false) String toDate,
                                                            @RequestParam("token") String token,
                                                            @RequestParam(value = "page_size", defaultValue = "1000", required = false) Integer pageSize,
                                                            @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page);

    @RequestMapping(value = "/rest/v1/activity/getFloorWiseRFIDetails", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getFloorWiseRFIDetails(@RequestParam(value = "user_id") int userId,
                                                                      @RequestParam(value = "token") String token,
                                                                      @RequestParam(value = "projectId",required = false) int projectId);

    // Activity Master Controller

    @RequestMapping(value = "/rest/v1/activity/master", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getActivitiesByUser(
            @RequestParam("user_id") int userId,
            @RequestParam("project_id") int projectId,
            @RequestParam("token") String token,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "lastSync", required = false) String lastSync);

    @RequestMapping(value = "/rest/v1/activity/checklist/master", method = RequestMethod.GET)
    ResponseEntity<LinkedHashMap<String, Object>> getChecklists(
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam("user_id") int user_id, @RequestParam("project_id") int project_id,
            @RequestParam("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync);

    @RequestMapping(value = "/activity/master/{pid}", method = RequestMethod.GET)
    ModelAndView activityMaster(@PathVariable("pid") int pid);

    @RequestMapping(value = "/activity/master/find", method = RequestMethod.GET)
    Object getActivityChecklistMaster();

    @RequestMapping(value = "/activity/create", method = RequestMethod.POST)
    Object postActivityData(@RequestBody String data);

    @RequestMapping(value = "/activity/checklist/update", method = RequestMethod.POST)
    Object addCheckListQuestions(@RequestBody String data);

    // CRFI Controller

    @Deprecated
    @RequestMapping(value = "/rest/v1/activity/request/findall", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllActivityRequestsByProjectId(@RequestParam("pid") long pid);

    @RequestMapping(value = "/rest/v1/activity/request/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getAllActivityRequestsByFilter(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/activity/request/find_sp", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getAllActivityRequestsBySP(@RequestBody Map<String, Object> requestParam);

//    @RequestMapping(value = "/rest/v1/activity/request/find", method = RequestMethod.POST)
//    ResponseEntity<Map<String, Object>> getAllActivityRequestBySP(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/activity/request/findHistory_Old", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getAllActivityRequestHistoryByIds(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/activity/request/findHistory", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getRFHistorybySP(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/activity/request/createorupdate", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> createActivityRequest(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/activity/request/checklist/create", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> createActivityChecklist(@RequestBody Map<String, Object> requestParam);

    @Deprecated
    @RequestMapping(value = "/rest/v1/activity/request/checklist/update", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> updateActivityChecklist(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/activity/request/checklist/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getActivityChecklist(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/activity/request/getCRFIClosureReportData", method = RequestMethod.GET)
    ResponseEntity<Object> getCRFIClosureReportData(@RequestParam("crfiId") Integer crfiId);

    @RequestMapping(value = "/rest/v1/activity/request/generateCRFIClosureReportByCrfiId", method = RequestMethod.GET)
    ResponseEntity<Object> generateCRFIClosureReportByCrfiId(@RequestHeader(value = "userId") Integer userId,
                                                             @RequestHeader(value = "token") String token,
                                                             @RequestParam("crfiId") Integer crfiId);

    @RequestMapping(value = "/rest/v1/activity/request/checkDependantActivites", method = RequestMethod.GET)
    ResponseEntity<Object> checkDependantActivites(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @RequestParam("locationId") Integer locationId,
                                                   @RequestParam("activityId") Integer activityId,
                                                   @RequestParam("projectId") Integer projectId);

    @RequestMapping(value = "/rest/v1/activity/request/generateCRFIClosureExcelReportByCrfiId", method = RequestMethod.GET)
    ResponseEntity<Object> generateCRFIClosureExcelReportByCrfiId(@RequestHeader(value = "userId") Integer userId,
                                                                  @RequestHeader(value = "token") String token,
                                                                  @RequestParam("crfiId") Integer crfiId);

    @RequestMapping(value = "/rest/v1/activity/request/instruction", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> createInstructCrfi(@RequestBody Map<String, Object> requestParam);

    // Activity Type Of Work Mapping Controller

    @GetMapping(value = "/rest/v1/activityTypeOfWork", produces = "application/json")
    ResponseEntity<Object> getActivityTypeOfWorkMapping(@RequestHeader("user_id") int userId,
                                                        @RequestHeader("token") String token,
                                                        @RequestParam(value = "lastSync", required = false) String lastSync);


    // Activity Unit Mapping Controller

    @GetMapping(value = "activityUnitMapping")
    List<Object> getAllActivityUnitMapping();

    // Common Controller

    @RequestMapping(value = "/rest/v1/configuration", method = RequestMethod.GET)
    ResponseEntity<Object> getConfiguration(@RequestParam("package_id") String package_id);

    @RequestMapping(value = "/rest/v1/login", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username,
                                              @RequestParam("password") String password) throws Exception;

    @RequestMapping(value = "/rest/v1/checkotp", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> checkotp(@RequestParam("otp") int otp,
                                                 @RequestParam("user_id") int user_id,
                                                 @RequestParam("version") String version,
                                                 @RequestParam("device_code") String device_code);

    @RequestMapping(value = "/rest/v1/logout", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> logout(@RequestParam("user_id") int user_id,
                                               @RequestParam("version") String version,
                                               @RequestParam("device_code") String device_code);

    @RequestMapping(value = "/rest/v1/uploadMultipleFiles", method = RequestMethod.POST)
    ResponseEntity<Object> uploadMultipleFileHandler(@RequestParam("user_id") int userId,
                                                     @RequestParam("token") String token,
                                                     @RequestParam("event_id") int eventId,
                                                     @RequestParam("event_name") String eventName,
                                                     @RequestParam("file") MultipartFile[] files,
                                                     @RequestParam(value = "comment", required = false, defaultValue = "--") String comment);

    @RequestMapping(value = "/rest/v1/userDetails", method = RequestMethod.GET)
    ResponseEntity<Object> userDetails(@RequestParam("user_id") int user_id,
                                  @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/media/find", method = RequestMethod.GET)
    void doDownload(@RequestParam("media_url") String mediaUrl,
                    @RequestParam("user_id") int user_id,
                    @RequestParam("token") String token,
                    HttpServletResponse response) throws IOException;

    @RequestMapping(value = "/rest/v1/addDeviceInfo", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> addDeviceInfo(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/common/masterlist/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getMasterList(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/notification/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> findUnseensNotifications(@RequestParam("user_id") int userId, @RequestParam("token") String token,
                                                                        @RequestParam("project_id") int projectId);

    @RequestMapping(value = "/rest/v1/common/heartbeat", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> heartBeat(@RequestParam("user_id") int userId);

    @RequestMapping(value = "/rest/v1/common/usertrack/update", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> updateUserTrack(@RequestParam("user_id") List<Integer> userIds);

    @RequestMapping(value = "/rest/v1/common/updateRFISequence", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> updateSequenceCode();

    @RequestMapping(value = "/rest/v1/common/updateSyncPendingStatus", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> updateSyncPendingStatus();

    @RequestMapping(value = "/rest/v1/upload/signature", method = RequestMethod.POST)
    ResponseEntity<Object> uploadSignature(@RequestParam("user_id") int user_id,
                                           @RequestParam("token") String token,
                                           @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/rest/v1/common/getUnitMaster", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getUnitMaster(@RequestParam("user_id") int userId,
                                         @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/loginAD", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> loginAD(@RequestParam("username") String username,
                                   @RequestParam("password") String password);

    @RequestMapping(value = "/rest/v1/findCompanies", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> findCompanies(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token);

    @PostMapping(value = "/rest/v1/upload/signature", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadSignature(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestPart(value = "file") MultipartFile file);

    @PostMapping("/rest/v2/login")
    ResponseEntity<Map<String, Object>> loginAPI(@RequestParam("username") String username,
                                    @RequestParam("password") String password);

    @PostMapping("/rest/v1/users/userinfo")
    ResponseEntity<Object> addDeviceTokenAndAppVersion(@RequestHeader("user_id") int userId,
                                                       @RequestHeader("token") String token,
                                                       @RequestParam("deviceToken") String deviceToken,
                                                       @RequestParam(value = "appVersion", defaultValue = "1", required = false) String appVersion);

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Object> registerUser(@RequestBody Object userRegisterRequest);

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = {"application/json"})
    ResponseEntity<Object> registerUser(@RequestParam(value = "user_id") Integer userId);

    // Company Activity Controller

    @RequestMapping(value = "/rest/v1/getAllCompanyActivity_old", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getCompanyActivity(@RequestParam("user_id") int userId,
                                              @RequestParam("token") String token,
                                              @RequestParam(value = "project_id", defaultValue = "0", required = false) int projectId,
                                              @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                              @RequestParam(value = "page_num", defaultValue = "1", required = false) int page);

    @RequestMapping(value = "/rest/v1/getAllCompanyActivity", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getCompanyActivitySP(@RequestParam("user_id") int userId,
                                                @RequestParam("token") String token,
                                                @RequestParam(value = "project_id", defaultValue = "0", required = false) int projectId,
                                                @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                @RequestParam(value = "page_num", defaultValue = "1", required = false) int page);

    @RequestMapping(value = "/rest/v2/getAllCompanyActivity", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getCompanyActivityV2(@RequestParam("user_id") int userId,
                                                @RequestParam("token") String token,
                                                @RequestParam(value = "project_id", defaultValue = "0", required = false) int projectId,
                                                @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                @RequestParam(value = "page_num", defaultValue = "1", required = false) int page);

    // Cube Test Alone Controller

    @RequestMapping(value = "/rest/v1/cubeTestA/create", method = RequestMethod.POST, produces = { "application/json" })
    ResponseEntity<Object> createCubeTestA(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object cubeTestARequest);

    @RequestMapping(value = "/rest/v1/cubeTestA/update", method = RequestMethod.PUT, produces = { "application/json" })
    ResponseEntity<Object> updateCubeTestA(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object cubeTestARequest);

    @RequestMapping(value = "/rest/v1/cubeTestA/find", method = RequestMethod.GET, produces = { "application/json" })
    ResponseEntity<Object> fetchCubeTestA(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestParam(value = "project_id") int projectId,
                                          @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                          @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize);

    @RequestMapping(value = "/rest/v1/cubeTestA/find/cubeTestById", method = RequestMethod.GET, produces = { "application/json" })
    ResponseEntity<Map<String, Object>> fetchCubeTestAById(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestParam(value = "id") int cubeTestAById);

    // Cube Test Controller

    @RequestMapping(value = "/rest/v1/cubeTest/main/create", method = RequestMethod.POST, produces = { "application/json" })
    ResponseEntity<Object> createCubeTest(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestBody Object cubeTestRequest);

    @RequestMapping(value = "/rest/v1/cubeTest/update", method = RequestMethod.PUT, produces = { "application/json" })
    ResponseEntity<Object> updateCubeTest(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestBody Object cubeTestRequest);

    @RequestMapping(value = "/rest/v1/cubeTest/updateCubeTest", method = RequestMethod.PUT, produces = { "application/json" })
    ResponseEntity<Object> updateCubeTestObj(@RequestHeader(value = "userId") Integer userId,
                                             @RequestHeader(value = "token") String token,
                                             @RequestBody List<Object> updateCubeTestModel);

    @RequestMapping(value = "/rest/v1/cubeTest/main/find", method = RequestMethod.GET, produces = { "application/json" })
    ResponseEntity<Object> fetchCubeTest(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @RequestParam(value = "project_id") int projectId,
                                         @RequestParam(value = "crfi_id", defaultValue = "0", required = false) int crfi_id,
                                         @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                         @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize);

    @RequestMapping(value = "/rest/v1/cubeTest/find/cubeTestById", method = RequestMethod.GET, produces = { "application/json" })
    ResponseEntity<Map<String, Object>> fetchCubeTestById(@RequestHeader(value = "userId") Integer userId,
                                             @RequestHeader(value = "token") String token,
                                             @RequestParam(value = "id") int cubeTestId);

    // Dashboard Controller

    @RequestMapping(value = "/rest/v1/activity/firstTimeRight", method = RequestMethod.GET)
    ResponseEntity<Object> getFirstTimeRight(@RequestParam(value = "user_id", required = false) Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam(value = "from_date", required = false) String fromDate, @RequestParam(value = "to_date", required = false) String to_date, @RequestParam(value = "token", required = false) String token);

    @RequestMapping(value = "/rest/v1/activity/rejections", method = RequestMethod.GET)
    ResponseEntity<Object> getRejections(@RequestParam(value = "user_id", required = false) Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam(value = "from_date", required = false) String fromDate, @RequestParam(value = "to_date", required = false) String toDate, @RequestParam(value = "token", required = false) String token);

    @RequestMapping(value = "/rest/v1/activity/comparison", method = RequestMethod.GET)
    ResponseEntity<Object> getComparison(@RequestParam(value = "user_id", required = false) Integer userId, @RequestParam("module") String module, @RequestParam("status") String status, @RequestParam(value = "project_id", required = false) Integer projectId, @RequestParam(value = "activity_id", required = false) Integer activityId, @RequestParam(value = "company_id", required = false) Integer companyId, @RequestParam(value = "from_date", required = false) String fromDate, @RequestParam(value = "to_date", required = false) String toDate, @RequestParam(value = "token", required = false) String token);

    @RequestMapping(value = "/rest/v1/activity/turnAroundTime", method = RequestMethod.GET)
    ResponseEntity<Object> getTurnAroundTime(@RequestParam(value = "user_id", required = false) Integer userId, @RequestParam("module") String module, @RequestParam("status") String status, @RequestParam(value = "location_id", required = false) Integer locationId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam(value = "from_date", required = false) String fromDate, @RequestParam(value = "to_date", required = false) String toDate, @RequestParam(value = "token", required = false) String token);

    @RequestMapping(value = "/rest/v1/activity/ftr/lastSixMonths", method = RequestMethod.GET)
    ResponseEntity<Object> getLastSixMonthsFTR(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("duration") String duration, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/activity/ftr/lastSevenDays", method = RequestMethod.GET)
    ResponseEntity<Object> getLastSevenDaysFTR(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("duration") String duration, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/activity/ftr/month", method = RequestMethod.GET)
    ResponseEntity<Object> getActivityFTRByMonth(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("year") Integer year, @RequestParam("month") String month, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/activity/ftr/date", method = RequestMethod.GET)
    ResponseEntity<Object> getActivityFTRByDate(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("date") String date, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/activity/ftr/dateRange", method = RequestMethod.GET)
    ResponseEntity<Object> getFTRByDateRange(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/activity/rejection/lastSixMonths", method = RequestMethod.GET)
    ResponseEntity<Object> getLastSixMonthsRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("duration") String duration, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/activity/rejection/lastSevenDays", method = RequestMethod.GET)
    ResponseEntity<Object> getLastSevenDaysRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("duration") String duration, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/activity/rejection/dateRange", method = RequestMethod.GET)
    ResponseEntity<Object> getRejectionByDateRange(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/activity/rejection/tower/lastSixMonths", method = RequestMethod.GET)
    ResponseEntity<Object> getLastSixMonthsTowerRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam(value = "tower_id", required = false) Integer towerId, @RequestParam("duration") String duration, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/activity/rejection/tower/lastSevenDays", method = RequestMethod.GET)
    ResponseEntity<Object> getLastSevenDaysTowerRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam(value = "tower_id", required = false) Integer towerId, @RequestParam("duration") String duration, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/activity/rejection/tower/dateRange", method = RequestMethod.GET)
    ResponseEntity<Object> getTowerRejectionByDateRange(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam(value = "tower_id", required = false) Integer towerId, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/project/activity/ftr/lastSixMonths", method = RequestMethod.GET)
    ResponseEntity<Object> getLastSixMonthsActivityFTR(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("duration") String duration, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/project/activity/ftr/lastSevenDays", method = RequestMethod.GET)
    ResponseEntity<Object> getLastSevenDaysActivityFTR(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("duration") String duration, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/project/activity/ftr/dateRange", method = RequestMethod.GET)
    ResponseEntity<Object> getActivityFTRByDateRange(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/project/activity/rejection/lastSixMonths", method = RequestMethod.GET)
    ResponseEntity<Object> getLastSixMonthsActivityRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("duration") String duration, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/project/activity/rejection/lastSevenDays", method = RequestMethod.GET)
    ResponseEntity<Object> getLastSevenDaysActivityRejection(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("duration") String duration, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/project/activity/rejection/dateRange", method = RequestMethod.GET)
    ResponseEntity<Object> getActivityRejectionByDateRange(@RequestParam("user_id") Integer userId, @RequestParam("project_id") Integer projectId, @RequestParam("activity_id") Integer activityId, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("token") String token);

    // Location Master Controller

    @RequestMapping(value = "/location/master/", method = RequestMethod.GET)
    ModelAndView sendredirect();

   /* @RequestMapping(value = "/location/db/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> findLocation(@RequestBody String data);

    @RequestMapping(value = "/location/db/findAll", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> findLocationAll(@RequestBody String data);
*/
    @RequestMapping(value = "/location/db/create", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> createLocationDB(@RequestBody String data);

    @RequestMapping(value = "/location/db/reset", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> resetLocationDB();

    // Material Checklist Master Controller

    @RequestMapping(value = "/rest/v1/mrfichecklistmaster" , method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getMRFIMaster(
            @RequestHeader(value = "user_id") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize);

   // Material Inspection Request Controller

    @RequestMapping(value = "/rest/v1/material/inspection/request/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> findMaterialInspectionRequest(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/material/inspection/request/history/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> findMaterialInspectionRequestHistory(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/material/inspection/request/createorupdate", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> createUpdateMaterialInspectionRequest(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/material/inspection/request/test/create", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> createMaterialTestResult(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/material/inspection/request/test/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getMaterialTestResult(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/material/inspection/request/test/update", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> updateMaterialTestResult(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/material/inspection/request/checklist/create", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> createMrfiChecklist(@RequestBody Map<String, Object> requestParam);

    // Material Master Controller

    @RequestMapping(value = "/rest/v1/material/master/findall", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllMaterials(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page, @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize, @RequestParam("user_id") int user_id, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/material/master", method = RequestMethod.GET)
    ResponseEntity<LinkedHashMap<String, Object>> getMaterials(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page, @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize, @RequestParam(value = "project_id", defaultValue = "0", required = false) int project_id, @RequestParam("user_id") int user_id, @RequestParam("token") String token);

    @RequestMapping(value = "/material/master/{pid}", method = RequestMethod.GET)
    ModelAndView materialMaster(@PathVariable("pid") int project_id);

    @RequestMapping(value = "/material_test/find", method = RequestMethod.GET)
    Object getAllMaterialTests();

    @RequestMapping(value = "/material_details/find", method = RequestMethod.GET)
    Object getAllMaterialDetails();

    @RequestMapping(value = "/material/create", method = RequestMethod.POST)
    Object postMaterialData(@RequestBody String data);

    @RequestMapping(value = "/material/test/update", method = RequestMethod.POST)
    Object updateMaterialTest(@RequestBody String data);

    @RequestMapping(value = "/rest/v1/vendor/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getVendors(@RequestParam(value = "user_id", required = true) int userId, @RequestParam(value = "token", required = true) String token);

    // NCR Controller

    @RequestMapping(value = "/rest/v1/ncr/main/create", method = RequestMethod.POST, produces = { "application/json" })
    ResponseEntity<Map<String, Object>> createNCRInMainAndHistory(@RequestBody Map<String, Object> data);

    @Deprecated
    @RequestMapping(value = "/rest/v1/ncr/main/findAll", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getAllNcrByProjectId(@RequestParam("user_id") int user_id, @RequestParam("token") String token, @RequestParam("project_id") long pid);

    @RequestMapping(value = "/rest/v1/ncr/history/findAll", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getAllNcrHistoryByNcrId(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/ncr/main/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getAllNcrByFilter(@RequestBody Map<String, Object> requestParam);

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/rest/v1/ncr/main/update", method = RequestMethod.POST, produces = { "application/json" })
    ResponseEntity<Map<String, Object>> updateNcrMain(@RequestBody Map<String, Object> data);

    @RequestMapping(value = "/sendNCRNotification", method = RequestMethod.GET)
    ResponseEntity<Object> sendNCRNotification(@RequestParam(value = "level1LocationId") Integer level1LocationId,
                                                                    @RequestParam(value = "ncrId") Integer obsId,
                                                                    @RequestParam(value = "user_id") Integer userId);

    // Observation Master Controller

    @RequestMapping(value = "/rest/v1/observation/master/db/findall", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllObservationsFromDB(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
                                                                        @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                                        @RequestParam("user_id") int user_id,
                                                                        @RequestParam("token") String token);


    // Observation Request Controller

    @RequestMapping(value = "/rest/v1/observation/request/createorupdate", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> createUpdateObservationRequest(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/observation/request/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> findObservationRequest(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/observation/request/history/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> findObservationRequestHistory(@RequestBody Map<String, Object> requestParam);

    @Deprecated
    @RequestMapping(value = "/rest/v1/observation/request/update", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> updateObservationRequest(@RequestBody Map<String, Object> requestParam);

    @RequestMapping(value = "/rest/v1/observation/request/sendOBSNotification", method = RequestMethod.GET)
    ResponseEntity<Object> sendOBSNotification(@RequestParam(value = "level1LocationId") Integer level1LocationId, @RequestParam(value = "obsId") Integer obsId, @RequestParam(value = "user_id") Integer userId);

    @RequestMapping(value = "/rest/v1/observation/request/partial", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> createPartialObs(@RequestBody Map<String, Object> requestParam);


    // Location Master Controller

    @RequestMapping(value = "/location/db/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> findLocation(@RequestBody String data,
                                                     @RequestParam(value = "lastSync", required = false) String lastSync);

    @RequestMapping(value = "/rest/v1/location/db/findall", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllLocationsFromDB(@RequestParam(value = "project_id", required = true) int pid,
                                                              @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
                                                              @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                              @RequestParam("user_id") int user_id,
                                                              @RequestParam("token") String token);

    // Observation Master Controller

/*
    @RequestMapping(value = "/rest/v1/observation/master/db/findall", method = RequestMethod.GET)
    ResponseEntity<Object> getAllObservationsFromDB(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
                                                    @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                    HttpServletRequest request,
                                                    @RequestHeader(value = "userId") Integer userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestParam(value = "lastSync", required = false) String lastSync);
*/


    // Project Controller

    @RequestMapping(value = "/rest/v1/projects", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> restProjects(@RequestParam("user_id") int user_id,
                                                     @RequestParam("token") String token,
                                                     @RequestParam(value = "lastSync", required = false) String lastSync,
                                                     @RequestParam(value = "zoneId", required = false, defaultValue = "0") int zoneId,
                                                     @RequestParam(value = "fundId", required = false, defaultValue = "0") int fundId) throws JsonParseException, JsonMappingException, IOException;

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    ModelAndView projectById(@RequestParam("pid") int project_id);

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    ModelAndView projects();

    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    ModelAndView addProject(@ModelAttribute("project") Object project);

    @RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
    ModelAndView deleteProject(@RequestParam("pid") int project_id);

    @RequestMapping(value = "/updateProject", method = RequestMethod.GET)
    ModelAndView updateProject(@ModelAttribute("project") Object project);

    // QC TBT Controller

    @PostMapping(value = "/rest/v1/qcTBT")
    ResponseEntity<Map<String, Object>> saveQcTBT(
            @RequestHeader("user_id") int userId,
            @RequestHeader("token") String token,
            @RequestBody Object qcTBTRequest);

    @GetMapping(value = "/rest/v1/qcTBT")
    ResponseEntity<Map<String, Object>> getQcTBTByProjectId(
            @RequestHeader("user_id") int userId,
            @RequestHeader("token") String token,
            @RequestParam("projectId") int projectId);

    @GetMapping(value = "/rest/v1/getQcTbtById/{id}")
    ResponseEntity<Map<String, Object>> getQcTbtById(
            @RequestHeader("user_id") int userId,
            @RequestHeader("token") String token,
            @PathVariable(value = "id") Integer tbtId);


    // Report Controller

   /* @RequestMapping(value = "/rest/v1/crfi/report", method = RequestMethod.GET)
//  String buildCRFIReport(ActivityInspection activityInspection, List<Integer> userIds, ProjectService projectService, UserService userService, CommonService commonService, ActivityMasterService activityMasterService, LocationMasterService locationMasterService, ActivityRequestService activityRequestService, ConfigProperties configProperties) {
    String buildCRFIReport(Object projectService, Object userService,
                           Object commonService, Object activityMasterService,
                           Object locationMasterService, Object activityRequestService,
                           Object configProperties);*/

    @RequestMapping(value = "/rest/v1/ncr/report", method = RequestMethod.GET)
    String buildNCRReport();

    @RequestMapping(value = "/rest/v1/obs/report", method = RequestMethod.GET)
    String buildOBSReport() throws Exception;

    @RequestMapping(value = "/rest/v1/obs/filter/report", method = RequestMethod.GET)
    ResponseEntity<Object> safetyObsReportByFilter(@RequestParam(value = "fromDate") String fromDate,
                                                   @RequestParam(value = "toDate") String toDate,
                                                   @RequestParam(value = "userId") Integer userId);

    @RequestMapping(value = "/rest/v1/obs/escalation/report", method = RequestMethod.GET)
    void escalationOBSReport() throws Exception;

/*    @RequestMapping(value = "/rest/v1/orl/report", method = RequestMethod.GET)
//  String ORLMonthlyReport() {
    String ORLMonthlyReport(Object projectService, Object userService,
                            Object activityRequestService, Object materialInspectionRequestService,
                            Object commonService, Object ncrMainService, Object observationRequestService,
                            Object activityMasterService, Object locationMasterService, Object observationMasterService,
                            Object materialMasterService, Object configProperties, Object commonController);*/

    @RequestMapping(value = "/rest/v1/crfi/RFIReport", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getDataForRFIReport(@RequestParam(value = "user_id") int userId,
                                                            @RequestParam(value = "token") String token,
                                                            @RequestParam(value = "project_id") int projectId,
                                                            @RequestParam(value = "crfiId",required = false) int crfiId);

    @RequestMapping(value = "/rest/v1/obsreport", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getObsReport(@RequestParam(value = "user_id") int userId,
                                                     @RequestParam(value = "token") String token,
                                                     @RequestParam(value = "obsId",required = false) int obsId);

    @RequestMapping(value = "/rest/v1/downloadNcrPdf", method = RequestMethod.GET)
    ResponseEntity<Object> downloadNcrPdf(@RequestHeader("user_id") int user_id,
                                          @RequestHeader("token") String token,
                                          @RequestParam(name = "ncrId", required = true, defaultValue = "0") int ncrId);

    @RequestMapping("/rest/v1/downloadOBSPdf")
    ResponseEntity<Object> downloadObsPdf(@RequestHeader("user_id") int user_id,
                                          @RequestParam(value = "fromDate") String fromDate,
                                          @RequestParam(value = "toDate") String toDate,
                                          @RequestParam(value = "projectId") Integer projectId);

    // User Controller

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
    ResponseEntity<Object> getUser(@PathVariable("uid") int user_id);

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    ModelAndView logoutPage ();

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    ModelAndView users();

    @RequestMapping(value = "/user/{pid}/{uid}", method = { RequestMethod.GET })
    ModelAndView userById(@PathVariable("uid") String user_ids,
                          @PathVariable("pid") int project_id);

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    ModelAndView addUser(@ModelAttribute("user") Object user,
                         @RequestParam("pid") int project_id,
                         @RequestParam("role_id") int role_id);

    @RequestMapping(value = "/deleteUser", method = { RequestMethod.GET })
    ModelAndView deleteUser(@RequestParam("uid") int user_id,
                            @RequestParam("pid") int project_id,
                            RedirectAttributes redirectAttributes);

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    ModelAndView updateUser(@ModelAttribute("user") Object user,
                            @RequestParam("pid") int project_id);

    @RequestMapping(value = "/projectMembers/{id}", method = { RequestMethod.GET })
    ModelAndView usersByProject(@PathVariable("id") int project_id);

    @RequestMapping(value = "/updateProjectMembers", method = RequestMethod.POST)
    ModelAndView updateProjectMembers(@ModelAttribute("project_Members") Object project_Members,
                                       @RequestParam("userIds") String userIds);

    @RequestMapping(value = "/addProjectMembers", method = RequestMethod.POST)
    ModelAndView addProjectMembers(@RequestParam("user_id") List<Integer> user_id,
                                   @RequestParam("pid") int project_id,
                                   @RequestParam("role_id") int role_id);

    @RequestMapping(value="/checkEmailPhoneNo", method=RequestMethod.GET)
    String checkEmailPhoneNo(@RequestParam("name") String name,
                             @RequestParam("value") String value);

    // Weekly Report Controller

//    @RequestMapping(value = "/rest/v1/weekly/report", method = RequestMethod.GET)
//    void WeeklyReport(Object projectService, Object activityRequestService,
//                      Object configProperties) throws Exception;


}
