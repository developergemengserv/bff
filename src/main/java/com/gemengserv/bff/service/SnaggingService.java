package com.gemengserv.bff.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gemengserv.bff.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@FeignClient(name = "snagging", url = "http://localhost:8097", configuration = FeignConfig.class)
public interface SnaggingService
{
    // Activity controller

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

    @RequestMapping(value = "/rest/v1/userDetails", method = RequestMethod.GET)
    ResponseEntity<?> userDetails(@RequestParam("user_id") int user_id,
                                  @RequestParam("token") String token);

    @RequestMapping(value = "/rest/v1/media/find", method = RequestMethod.GET)
    void doDownload(@RequestParam("media_url") String mediaUrl,
                    @RequestParam("user_id") int user_id,
                    @RequestParam("token") String token,
                    HttpServletResponse response) throws IOException;

    @PostMapping(value = "/rest/v1/upload/signature", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadSignature(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestPart(value = "file") MultipartFile file);

    @PostMapping("/rest/v2/login")
    ResponseEntity<Object> loginAPI(@RequestParam("username") String username,
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

    // Emergency HelpLine Controller

    @GetMapping(value = "/getEmergencyHelpline")
    ResponseEntity<List<Object>> getAllEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
                                                         @RequestHeader(value = "token") String token,
                                                         int projectId);

    // Hazards Controller

    @PostMapping(value = "/hazards/upload/{projectId}", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadHazards(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @PathVariable(value = "projectId") Integer projectId,
                                         @RequestParam(value = "file") MultipartFile file);

    @GetMapping(value = "/getHazardsByProjectId/{project_id}")
    ResponseEntity<List<Object>> getHazardsByProjectId(@RequestHeader(value = "user_id") Integer userId,
                                                       @RequestHeader(value = "token") String token,
                                                       @PathVariable(value = "project_id") int projectId);


    // Location Master Controller

    @RequestMapping(value = "/addLocations", consumes = "multipart/form-data", method = RequestMethod.POST)
    String addLocations(@RequestParam(value = "userId") int userId,
                        @RequestParam(value = "file") MultipartFile file,
                        @RequestParam(value = "projectId") int projectId);

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

    @RequestMapping(value = "/rest/v1/observation/master/db/findall", method = RequestMethod.GET)
    ResponseEntity<Object> getAllObservationsFromDB(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
                                                    @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                    @RequestHeader(value = "userId") Integer userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestParam(value = "lastSync", required = false) String lastSync);

    // Progress Report Controller

    @PostMapping(value = "/progressReport")
    ResponseEntity<Object> saveProgressReport(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestBody Object progressReportRequest);

    @GetMapping(value = "/progressReport")
    ResponseEntity<List<Object>> getProgressReport(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token);

    @GetMapping(value = "/progressReport/{id}")
    ResponseEntity<Object> getProgressReportById(@RequestHeader(value = "userId") Integer userId,
                                                 @RequestHeader(value = "token") String token,
                                                 @PathVariable(value = "id") long progressReportId);

    // Project Controller

    @RequestMapping(value = "/rest/v1/projects", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> restProjects(@RequestParam("user_id") int user_id,
                                                     @RequestParam("token") String token,
                                                     @RequestParam(value = "lastSync", required = false) String lastSync) throws JsonParseException, JsonMappingException, IOException;

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    ModelAndView projectById(@RequestParam("pid") int project_id);

    // Report Controller

    @RequestMapping(value = "/rorReport", method = RequestMethod.GET)
    void rorReport();

    @RequestMapping(value = "/ptwReport", method = RequestMethod.POST)
    void ptwReport(@RequestBody Object ptwReportRequest);

    @RequestMapping(value = "/safetyObsReportByFilterForDaily", method = RequestMethod.GET)
    ResponseEntity<Object> safetyObsReportByFilterForDaily( @RequestParam(value = "projectId") Integer projectId);

    @RequestMapping(value = "/safetyOBSWord", method = RequestMethod.GET)
    ResponseEntity<Object> safetyOBSReport(@RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId,
                                           @RequestParam(value = "date", required = false, defaultValue = "") String date,
                                           @RequestParam(value = "locationId", required = false, defaultValue = "0") int locationId,
                                           @RequestParam(value = "level", required = false, defaultValue = "0") int level);

    @RequestMapping(value = "/safetyOBSWordDetailed", method = RequestMethod.GET)
    ResponseEntity<Object> safetyOBSReportDetailed(@RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId,
                                                   @RequestParam(value = "date", required = false, defaultValue = "") String date,
                                                   @RequestParam(value = "locationId", required = false, defaultValue = "0") int locationId,
                                                   @RequestParam(value = "level", required = false, defaultValue = "0") int level);


    @RequestMapping(value = "/safetyObsReport", method = RequestMethod.GET)
    void safetyObsReport();

    @RequestMapping(value = "/escalateOBSReport", method = RequestMethod.GET)
    void escalateOBSReport();

    @RequestMapping(value = "/safetyObsReportByFilter", method = RequestMethod.GET)
    ResponseEntity<Object> safetyObsReportByFilter(@RequestParam(value = "fromDate") String fromDate,
                                                   @RequestParam(value = "toDate") String toDate,
                                                   @RequestParam(value = "userId") Integer userId,
                                                   @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId);

    @RequestMapping(value = "/manPowerReport", method = RequestMethod.GET)
    ResponseEntity<Object> safetyManPowerReport(@RequestParam(value = "fromDate") String fromDate,
                                                @RequestParam(value = "toDate") String toDate,
                                                @RequestParam(value = "userId") Integer userId,
                                                @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId);

    @RequestMapping(value = "/safetyTbtExcelReport", method = RequestMethod.GET)
    ResponseEntity<Object> safetyTbtExcelReport(@RequestParam(value = "fromDate") String fromDate,
                                                @RequestParam(value = "toDate") String toDate,
                                                @RequestParam(value = "userId") Integer userId,
                                                @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId);

    @RequestMapping(value = "/safetyMeetingExcelReport", method = RequestMethod.GET)
    ResponseEntity<Object> safetyMeetingExcelReport(@RequestParam(value = "fromDate") String fromDate,
                                                    @RequestParam(value = "toDate") String toDate,
                                                    @RequestParam(value = "userId") Integer userId,
                                                    @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId);

    @RequestMapping(value = "/safetyEqExcelReport", method = RequestMethod.GET)
    ResponseEntity<Object> safetyEqExcelReport(@RequestParam(value = "fromDate") String fromDate,
                                               @RequestParam(value = "toDate") String toDate,
                                               @RequestParam(value = "userId") Integer userId,
                                               @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId);

    @RequestMapping(value = "/safetyPTWExcelReport", method = RequestMethod.GET)
    ResponseEntity<Object> safetyPTWExcelReport(@RequestParam(value = "fromDate") String fromDate,
                                                @RequestParam(value = "toDate") String toDate,
                                                @RequestParam(value = "userId") Integer userId,
                                                @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId);

    @GetMapping("/downloadMemoPdf")
    ResponseEntity<Object> downloadMemoPdf(@RequestHeader("user_id") int user_id,
                                           @RequestHeader("token") String token,
                                           @RequestParam(name = "obsId", required = false, defaultValue = "0") int obsId);

    @GetMapping("/downloadOBSPdf")
    ResponseEntity<Object> downloadOBSPdf(@RequestHeader("user_id") int user_id,
                                          @RequestHeader("token") String token,
                                          @RequestParam(name = "obsId", required = false, defaultValue = "0") int obsId);

    @GetMapping("/downloadIncidentPdf")
    ResponseEntity<Object> downloadIncidentPdf(@RequestHeader("user_id") int user_id,
                                               @RequestHeader("token") String token,
                                               @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId);

    @GetMapping("/sendIncidentPdf")
    ResponseEntity<Object> sendIncidentPdf(@RequestHeader("user_id") int user_id,
                                           @RequestHeader("token") String token,
                                           @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId);

    @GetMapping("/downloadTBTPdf")
    ResponseEntity<Object> downloadTBTPdf(@RequestHeader("user_id") int user_id,
                                          @RequestHeader("token") String token,
                                          @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId,
                                          @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType);

    @GetMapping("/downloadGPPdf")
    ResponseEntity<Object> downloadGPPdf(@RequestHeader("user_id") int user_id,
                                         @RequestHeader("token") String token,
                                         @RequestParam(name = "goodPracticeId", required = true, defaultValue = "0") int goodPracticeId);

    @RequestMapping(value = "/equipmentReport", method = RequestMethod.POST)
    ResponseEntity<Object> equipmentReport(@RequestBody Object  equipmentReportRequest);

    @RequestMapping(value = "/downloadInspectionPdf", method = RequestMethod.GET)
    ResponseEntity<Object> inspectionReport(@RequestHeader("user_id") int user_id,
                                            @RequestHeader("token") String token,
                                            @RequestParam(name = "insId", required = true, defaultValue = "0") int insId);

    @RequestMapping(value = "/tbtReportPdf", method = RequestMethod.GET)
    ResponseEntity<Object> tbtReport(@RequestHeader("user_id") int user_id,
                                     @RequestHeader("token") String token,
                                     @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId,
                                     @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType);

    @RequestMapping(value = "/meetingReportPdf", method = RequestMethod.GET)
    ResponseEntity<Object> meetingReport(@RequestHeader("user_id") int user_id,
                                         @RequestHeader("token") String token,
                                         @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId,
                                         @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType);

    @RequestMapping(value = "/downloadWPPdf", method = RequestMethod.GET)
    ResponseEntity<Object> ewpReport(@RequestHeader("user_id") int user_id,
                                     @RequestHeader("token") String token,
                                     @RequestParam(name = "wpId", required = true, defaultValue = "0") int wpId,
                                     @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType);

    @RequestMapping(value = "/firstAidReportPdf", method = RequestMethod.GET)
    ResponseEntity<Object> firstAidReport(@RequestHeader("user_id") int user_id,
                                          @RequestHeader("token") String token,
                                          @RequestParam(name = "firstAidId", required = true, defaultValue = "0") int firstAidId,
                                          @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType);

    @RequestMapping(value = "/nearMissReportPdf", method = RequestMethod.GET)
    ResponseEntity<Object> nearMissReport(@RequestHeader("user_id") int user_id,
                                          @RequestHeader("token") String token,
                                          @RequestParam(name = "nearMissId", required = true, defaultValue = "0") int nearMissId,
                                          @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType);

    @GetMapping("/downloadWorkerPdf")
    ResponseEntity<Object> downloadWorkerPdf(@RequestHeader("user_id") int user_id,
                                             @RequestHeader("token") String token,
                                             @RequestParam(name = "workerId", required = true, defaultValue = "0") int workerId,
                                             @RequestParam(name = "requestType", required = true, defaultValue = "Mobile") String requestType);

    // Safe Man Hours Controller

    @PostMapping(value = "/safety/safeManHrs")
    ResponseEntity<Object> saveManHrs(@RequestHeader(value = "userId") Integer userId,
                                      @RequestHeader(value = "token") String token,
                                      @RequestBody Object manHrsRequest);

    @GetMapping(value = "/safety/safeManHrs")
    ResponseEntity<List<Object>> getManHrs(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token);

    @GetMapping(value = "/safety/getManPower")
    ResponseEntity<Object> getManPower(@RequestParam (value = "projectId") Integer projectId);

    @GetMapping(value = "/safety/safeManHrsByProjectId")
    ResponseEntity<List<Object>> getManHrsByProjectId(@RequestHeader(value = "userId") Integer userId,
                                                      @RequestHeader(value = "token") String token,
                                                      @RequestParam(value = "project_id") Integer projectId);

    @GetMapping(value = "/safety/safeManHrsBySP")
    ResponseEntity<Object> getManHrsByProjectId(@RequestHeader(value = "userId") Integer userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestParam(value = "project_id") Integer projectId,
                                                @RequestParam(value = "page_num") int pageNum,
                                                @RequestParam(value = "page_size") int pageSize);

    @GetMapping(value = "/safety/safeManHrsById")
    ResponseEntity<Object> safeManHrsById(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestParam(value = "manHrsId") Integer manHrsId);

    // Safety Controller

    @RequestMapping(value = "/safety/obs/create", method = RequestMethod.POST)
    ResponseEntity<Object> createSafetyObservation(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @RequestBody Object obsRequest);

    @RequestMapping(value = "/safety/obs/update", method = RequestMethod.POST)
    ResponseEntity<Object> updateSafetyObservation(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @RequestBody Object updateRequest);

    @RequestMapping(value = "/safety/obs/find", method = RequestMethod.POST)
    ResponseEntity<Object> findSafetyObservation(@RequestHeader(value = "userId") Integer userId,
                                                 @RequestHeader(value = "token") String token,
                                                 @RequestParam(value = "lastSync", required = false) String lastSync,
                                                 @RequestBody Object request);


    @RequestMapping(value = "/safety/obs/find/{obsId}", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyObservationByObsId(@RequestHeader(value = "userId") Integer userId,
                                                        @RequestHeader(value = "token") String token,
                                                        @PathVariable(value = "obsId") Integer obsId);

    @RequestMapping(value = "/safety/obs/history/find", method = RequestMethod.POST)
    ResponseEntity<Object> findObservationRequestHistory(@RequestHeader(value = "userId") Integer userId,
                                                         @RequestHeader(value = "token") String token,
                                                         @RequestBody Object request);


    // MASTER API

    @RequestMapping(value = "/safety/master/unsafeAct/findAll", method = RequestMethod.GET)
    ResponseEntity<Object> findAllUnsafeAct(@RequestHeader(value = "userId") Integer userId,
                                            @RequestHeader(value = "token") String token,
                                            @RequestParam(value = "lastSync", required = false) String lastSync);

    @RequestMapping(value = "/safety/master/unsafeCondition/findAll", method = RequestMethod.GET)
    ResponseEntity<Object> findAllUnsafeCondition(@RequestHeader(value = "userId") Integer userId,
                                                  @RequestHeader(value = "token") String token,
                                                  @RequestParam(value = "lastSync", required = false) String lastSync);


    @RequestMapping(value = "/safety/master/typeOfWork/findAll", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> findAllTypeOfWork(@RequestParam("user_id") int userId,
                                                          @RequestParam("token") String token,
                                                          @RequestParam(value = "lastSync", required = false) String lastSync);

    // FOR NCR SPECIFIC API

    @RequestMapping(value = "/safety/ncr/create", method = RequestMethod.POST)
    ResponseEntity<Object> createSafetyNCR(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object request);

    @RequestMapping(value = "/safety/ncr/update", method = RequestMethod.POST)
    ResponseEntity<Object> updateSafetyNcr(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object request);

    @RequestMapping(value = "/safety/ncr/find", method = RequestMethod.POST)
    ResponseEntity<Object> findSafetyNCR(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @RequestParam(value = "lastSync", required = false) String lastSync,
                                         @RequestBody Object ncrRequest);

    @RequestMapping(value = "/safety/ncr/history/find", method = RequestMethod.POST)
    ResponseEntity<Object> findNCRHistory(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestBody Object ncrRequest);

    // FOR PTW SPECIFIC API

    @RequestMapping(value = "/safety/ptw", method = RequestMethod.POST)
    ResponseEntity<Object> createSafetyPTW(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object ptwRequest);

    @RequestMapping(value = "/safety/ptw", method = RequestMethod.PUT)
    ResponseEntity<Object> updateSafetyPTW(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object ptwRequest);

    @RequestMapping(value = "/safety/ptwStatus", method = RequestMethod.PUT)
    ResponseEntity<Object> updatePTWStatus(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object request);

    @RequestMapping(value = "/safety/ptwClose", method = RequestMethod.PUT)
    ResponseEntity<Object> updateUnclosedPTW();

    @RequestMapping(value = "/safety/ptw/find", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyPTW(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                         @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                         @RequestParam(value = "project_id") Integer projectId,
                                         @RequestParam(value = "lastSync", required = false) String lastSync,
                                         @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id);


    @RequestMapping(value = "/safety/ptw/findMin", method = RequestMethod.GET)
    ResponseEntity<Object> findPTW(@RequestHeader(value = "userId") Integer userId,
                                   @RequestHeader(value = "token") String token,
                                   @RequestParam(value = "project_id") int projectId,
                                   @RequestParam(value = "lastSync", required = false) String lastSync,
                                   @RequestParam(value = "locationLevel1Id", required = false, defaultValue = "0") int locationLevel1Id);

    @RequestMapping(value = "/safety/ptw/findsp", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyPTWSP(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                           @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                           @RequestParam(value = "project_id") Integer projectId);

    @RequestMapping(value = "/safety/ptw/find/V2", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyPTWW(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                          @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                          @RequestParam(value = "project_id") Integer projectId,
                                          @RequestParam(value = "lastSync", required = false) String lastSync,
                                          @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id);

    @RequestMapping(value = "/safety/ptw/find/{ptwId}", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyPTWById(@RequestHeader(value = "userId") Integer userId,
                                             @RequestHeader(value = "token") String token,
                                             @PathVariable(value = "ptwId") Integer ptwId);

    @RequestMapping(value = "/safety/ptw/findsp/{ptwId}", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyPTWByIdSP(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token,
                                               @PathVariable(value = "ptwId") Integer ptwId);

    @RequestMapping(value = "/safety/ptw/find/V2/{ptwId}", method = RequestMethod.GET)
    ResponseEntity<Object> findPTWById(@RequestHeader(value = "userId") Integer userId,
                                       @RequestHeader(value = "token") String token,
                                       @PathVariable(value = "ptwId") Integer ptwId);

    @RequestMapping(value = "/safety/findPtwByFilter", method = RequestMethod.GET)
    ResponseEntity<Object> findPTWByFilter(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestParam(value = "projectId") Integer projectId,
                                           @RequestParam(value = "fromDate") String fromDate,
                                           @RequestParam(value = "toDate") String toDate);

    @RequestMapping(value = "/safety/ptw/history/find", method = RequestMethod.POST)
    ResponseEntity<Object> findPTWHistory(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestBody Object ptwHistoryFindRequest);

    @RequestMapping(value = "/safety/findPtwCount", method = RequestMethod.POST)
    ResponseEntity<Object> findPtwCount(@RequestHeader(value = "userId") Integer userId,
                                        @RequestHeader(value = "token") String token,
                                        @RequestBody Object dashboardCountRequest);

    @RequestMapping(value = "/safety/findObsCount", method = RequestMethod.POST)
    ResponseEntity<Object> findObsCount(@RequestHeader(value = "userId") Integer userId,
                                        @RequestHeader(value = "token") String token,
                                        @RequestBody Object dashboardCountRequest);

    @RequestMapping(value = "/safety/findEcCount", method = RequestMethod.POST)
    ResponseEntity<Object> findEcCount(@RequestHeader(value = "userId") Integer userId,
                                       @RequestHeader(value = "token") String token,
                                       @RequestBody Object dashboardCountRequest);

    @RequestMapping(value = "/safety/findTbtCount", method = RequestMethod.POST)
    ResponseEntity<Object> findTbtCount(@RequestHeader(value = "userId") Integer userId,
                                        @RequestHeader(value = "token") String token,
                                        @RequestBody Object dashboardCountRequest);

    @RequestMapping(value = "/safety/findManPowerCount", method = RequestMethod.POST)
    ResponseEntity<Object> findManPowerCount(@RequestHeader(value = "userId") Integer userId,
                                             @RequestHeader(value = "token") String token,
                                             @RequestBody Object dashboardCountRequest);

    @RequestMapping(value = "/safety/findCommitteeCount", method = RequestMethod.POST)
    ResponseEntity<Object> findCommitteeCount(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestBody Object dashboardCountRequest);

    // FOR COMMON SPECIFIC API

    @PostMapping(value = "/safety/uploadMultipleFiles", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadMultipleFiles(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestParam("eventId") Integer eventId,
                                               @RequestParam("eventName") String eventName,
                                               @RequestParam("file") MultipartFile[] files);

    @PostMapping(value = "/safety/uploadCheckListMedia", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadCheckListMedia(@RequestHeader(value = "userId") Integer userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestParam("checklistAnswerId") Integer checklistAnswerId,
                                                @RequestParam("file") MultipartFile[] files);
    @RequestMapping(value = "/safety/findUsersByProjectIdAndRoleId", method = RequestMethod.GET)
    ResponseEntity<Object> findUsersByProjectIdAndRoleId(@RequestHeader(value = "userId") Integer userId,
                                                         @RequestHeader(value = "token") String token,
                                                         @RequestParam(value = "project_id") Integer projectId,
                                                         @RequestParam(value = "roleId") Integer roleId,
                                                         @RequestParam(value = "lastSync", required = false) String lastSync);

    @RequestMapping(value = "/safety/sendOBSNotification", method = RequestMethod.GET)
    ResponseEntity<Object> sendOBSNotification(@RequestHeader(value = "level1LocationId") Integer level1LocationId,
                                               @RequestHeader(value = "obsId") Integer obsId,
                                               @RequestHeader(value = "user_id") Integer userId);

    // MASTER API

    @RequestMapping(value = "/safety/master/answerType/findAll", method = RequestMethod.GET)
    ResponseEntity<Object> findAllAnswerType(@RequestHeader(value = "userId") Integer userId,
                                             @RequestHeader(value = "token") String token,
                                             @RequestParam(value = "lastSync", required = false) String lastSync);

    @RequestMapping(value = "/safety/master/checklistQuestions/findAll", method = RequestMethod.GET)
    ResponseEntity<Object> findAllChecklistQuestions(@RequestHeader(value = "userId") Integer userId,
                                                     @RequestHeader(value = "token") String token,
                                                     @RequestParam(value = "lastSync", required = false) String lastSync);

    @GetMapping(value = "/safety/master/typeOfWorkChecklistMapping")
    ResponseEntity<Object> findAllTypeOfWorkChecklistMapping(@RequestHeader(value = "userId") Integer userId,
                                                             @RequestHeader(value = "token") String token,
                                                             @RequestParam(value = "lastSync", required = false) String lastSync);

    //Equipment

    @RequestMapping(value = "/safety/equipment", method = RequestMethod.POST)
    ResponseEntity<Object> saveEquipment(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @RequestBody Object equipmentRequest);

    @RequestMapping(value = "/safety/equipment", method = RequestMethod.PUT)
    ResponseEntity<Object> updateEquipment(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object equipmentRequest);

    @RequestMapping(value = "/safety/equipment/find_old", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyEquipment(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                               @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                               @RequestParam(value = "project_id") Integer projectId,
                                               @RequestParam(value = "lastSync", required = false) String lastSync,
                                               @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id);

    @RequestMapping(value = "/safety/equipment/find", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyEquipmentBySP(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                                   @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                                   @RequestParam(value = "project_id") Integer projectId,
                                                   @RequestParam(value = "lastSync", required = false) String lastSync,
                                                   @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id);

    @RequestMapping(value = "/safety/equipment/history/find", method = RequestMethod.POST)
    ResponseEntity<Object> findEQHistory(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @RequestBody Object eqHistoryFindRequest);

    @RequestMapping(value = "/safety/equipment/find/{equipmentId}", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyEquipmentById(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @PathVariable(value = "equipmentId") Integer equipmentId);

    // Observation & Statistics
    @RequestMapping(value = "/safety/findOBSByFilter", method = RequestMethod.GET)
    ResponseEntity<Object> findOBSByFilter(@RequestParam(value = "fromDate") String fromDate,
                                           @RequestParam(value = "toDate") String toDate,
                                           @RequestParam(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/safety/statistics/{projectId}", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyStatistics(@RequestHeader(value = "userId") Integer userId,
                                                @RequestHeader(value = "token") String token,
                                                @PathVariable(value = "projectId") Integer projectId);

    // Bulletin
    @RequestMapping(value = "/safety/bulletin", method = RequestMethod.POST)
    ResponseEntity<Object> sendBulletin(@RequestHeader(value = "userId") Integer userId,
                                        @RequestHeader(value = "token") String token,
                                        @RequestBody Object bulletinRequest);

    @RequestMapping(value = "/safety/bulletin/{projectId}", method = RequestMethod.GET)
    ResponseEntity<Object> findBulletinByProjectId(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @PathVariable(value = "projectId") Integer projectId);

    // Digital Library & PTW Support
    @RequestMapping(value = "/safety/getSafetyDigitalLibrary", method = RequestMethod.GET)
    ResponseEntity<Object> getSafetyDigitalLibrary(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @RequestParam(value = "project_id") int projectId);

    @RequestMapping(value = "/safety/ptw/getPendingPtw", method = RequestMethod.GET)
    ResponseEntity<Object> getPendingPtw(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @RequestParam int projectId);

    // Good Practices
    @PostMapping(value = "/safety/safetyGoodPractices")
    ResponseEntity<Object> saveGoodPractices(@RequestHeader(value = "userId") Integer userId,
                                             @RequestHeader(value = "token") String token,
                                             @RequestBody Object goodPracticesRequest);

    @GetMapping(value = "/safety/safetyGoodPractices")
    ResponseEntity<Object> getGoodPractices(@RequestHeader(value = "userId") Integer userId,
                                            @RequestHeader(value = "token") String token);

    // Master Data (Zones, Funds, Workers)
    @GetMapping(value = "/safety/zones")
    ResponseEntity<Object> getZones(@RequestHeader(value = "userId") Integer userId,
                                    @RequestHeader(value = "token") String token);

    @GetMapping(value = "/safety/funds")
    ResponseEntity<Object> getFunds(@RequestHeader(value = "userId") Integer userId,
                                    @RequestHeader(value = "token") String token);

    @GetMapping(value = "/safety/getSafetyWorkerType")
    ResponseEntity<Object> getSafetyWorkerType(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token);

    @RequestMapping(value = "/safety/workers/history/find", method = RequestMethod.POST)
    ResponseEntity<Object> findWorkerRequestHistory(@RequestHeader(value = "userId") Integer userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestBody Object request);

    // Safety Incident Controller

    @RequestMapping(value = "/safety/incident", method = RequestMethod.POST)
    ResponseEntity<Object> saveIncident(@RequestHeader(value = "userId") Integer userId,
                                        @RequestHeader(value = "token") String token,
                                        @RequestBody Object request);

    @RequestMapping(value = "/safety/incident_old", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyIncident(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                              @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                              @RequestParam(value = "project_id") Integer projectId);

    @RequestMapping(value = "/safety/incident/{incidentId}", method = RequestMethod.GET)
    ResponseEntity<Object> findIncidentById(@RequestHeader(value = "userId") Integer userId,
                                            @RequestHeader(value = "token") String token,
                                            @PathVariable(value = "incidentId") Integer incidentId);

    // Near Miss APIs
    @RequestMapping(value = "/addNearMiss", method = RequestMethod.POST)
    ResponseEntity<Object> saveSafetyNearMiss(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestBody Object safetyNearMissRequest);

    @RequestMapping(value = "/getNearMiss", method = RequestMethod.GET)
    ResponseEntity<Object> getNearMiss(@RequestHeader(value = "userId") Integer userId,
                                       @RequestHeader(value = "token") String token,
                                       @RequestParam(value = "projectId") Integer projectId);

    @RequestMapping(value = "/safety/incident", method = RequestMethod.GET)
    ResponseEntity<Object> getNearMissIncident(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestParam(value = "projectId") Integer projectId);

    @RequestMapping(value = "/getNearMissById", method = RequestMethod.GET)
    ResponseEntity<Object> getNearMissById(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestParam(value = "nearMissId") Integer nearMissId);

    @RequestMapping(value = "/getNearMissHistory", method = RequestMethod.GET)
    ResponseEntity<Object> getNearMissHistory(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestParam(value = "nearMissId") Integer nearMissId);

    @PutMapping(value = "/updateNearMiss")
    ResponseEntity<Object> updateNearMissById(@RequestHeader(value = "userId") int userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestBody Object request);

    @RequestMapping(value = "/getNearMissAPHistory", method = RequestMethod.GET)
    ResponseEntity<Object> getNearMissAPHistory(@RequestHeader(value = "userId") Integer userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestParam(value = "nearMissId") Integer nearMissId);

    // First Aid APIs
    @RequestMapping(value = "/addFirstAid", method = RequestMethod.POST)
    ResponseEntity<Object> saveSafetyFirstAid(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestBody Object safetyFirstAidRequest);

    @RequestMapping(value = "/getFirstAid", method = RequestMethod.GET)
    ResponseEntity<Object> getFirstAid(@RequestHeader(value = "userId") Integer userId,
                                       @RequestHeader(value = "token") String token,
                                       @RequestParam(value = "projectId") Integer projectId);

    @RequestMapping(value = "/getFirstAidById", method = RequestMethod.GET)
    ResponseEntity<Object> getFirstAidById(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestParam(value = "firstAidId") Integer firstAidId);

    @RequestMapping(value = "/getFirstAidHistory", method = RequestMethod.GET)
    ResponseEntity<Object> getFirstAidHistory(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestParam(value = "firstAidId") Integer firstAidId);

    @PutMapping(value = "/updateFirstAid")
    ResponseEntity<Object> updateFirstAidById(@RequestHeader(value = "userId") int userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestBody Object updateFirstAidRequest);

    @RequestMapping(value = "/getFirstAidAPHistory", method = RequestMethod.GET)
    ResponseEntity<Object> getFirstAidAPHistory(@RequestHeader(value = "userId") Integer userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestParam(value = "firstAidId") Integer firstAidId);

    // Safety TBT Controller

    @PostMapping(value = "/SafetyTBT")
    ResponseEntity<Object> saveSafetyTBT(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @RequestBody Object safetyTBTRequest);

    @GetMapping(value = "/SafetyTBT")
    ResponseEntity<Object> getSafetyTBT(@RequestHeader(value = "userId") Integer userId,
                                        @RequestHeader(value = "token") String token,
                                        @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId);

    @GetMapping(value = "/SafetyTBTSP")
    ResponseEntity<Object> getSafetyTBTSP(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId);

    @GetMapping(value = "/SafetyTBTSP2")
    ResponseEntity<Object> getSafetyTBTSP2(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId);

    @GetMapping(value = "/SafetyTBTSPWithPageWise")
    ResponseEntity<Object> SafetyTBTSPWithPageWise(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId,
                                                   @RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "0") Integer pageSize);

    @GetMapping(value = "/SafetyTBTTopics")
    ResponseEntity<Object> getSafetyTBTTopics(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token);

    @GetMapping(value = "/getSafetyTbtById/{id}")
    ResponseEntity<Object> getSafetyTbtById(@RequestHeader(value = "userId") Integer userId,
                                            @RequestHeader(value = "token") String token,
                                            @PathVariable(value = "id") Integer tbtId);

    @GetMapping(value = "/getSafetyTbtByIdWithSP")
    ResponseEntity<Object> getSafetyTbtByIdWithSP(@RequestHeader(value = "userId") Integer userId,
                                                  @RequestHeader(value = "token") String token,
                                                  @RequestParam(value = "tbtId") Integer tbtId);

    @GetMapping(value = "/getSafetyTbtHistoryById/{id}")
    ResponseEntity<Object> getSafetyTbtHistoryById(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @PathVariable(value = "id") Integer tbtId);

    @GetMapping(value = "/SafetyTBTMaster")
    ResponseEntity<Object> getSafetyTBTMaster(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token);

    @PutMapping(value = "/updateSafetyTBT")
    ResponseEntity<Object> updateSafetyTBT(@RequestHeader(value = "user_id") int userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object request);

    // Safety Workers Controller

    @PostMapping(value = "/SafetyWorkers")
    ResponseEntity<Object> saveSafetyWorkers(@RequestHeader(value = "user_id") int userId,
                                             @RequestHeader(value = "token") String token,
                                             @RequestBody Object safetyWorkersRequest);

    @GetMapping(value = "/SafetyWorkers")
    ResponseEntity<Object> getSafetyWorkers(@RequestHeader(value = "user_id") int userId,
                                            @RequestHeader(value = "token") String token,
                                            @RequestParam(value = "project_id") long projectId,
                                            @RequestParam(value = "company_id", required = false, defaultValue = "0") long companyId);

    @GetMapping(value = "/SafetyWorkersByPagination")
    ResponseEntity<Object> getSafetyWorkersWithPagination(@RequestHeader(value = "user_id") int userId,
                                                          @RequestHeader(value = "token") String token,
                                                          @RequestParam(value = "project_id") long projectId,
                                                          @RequestParam(value = "company_id", required = false, defaultValue = "0") long companyId,
                                                          @RequestParam(value = "page_num") int pageNum,
                                                          @RequestParam(value = "page_size") int pageSize);

    @GetMapping(value = "/getApprovedWorkers")
    ResponseEntity<Object> getSafetyWorkersForSelection(@RequestHeader(value = "user_id") int userId,
                                                        @RequestHeader(value = "token") String token,
                                                        @RequestParam(value = "project_id") long projectId,
                                                        @RequestParam(value = "company_id", required = false, defaultValue = "0") long companyId);

    @GetMapping(value = "/SafetyWorkers/{id}")
    ResponseEntity<Object> getSafetyWorkersById(@RequestHeader(value = "user_id") int userId,
                                                @RequestHeader(value = "token") String token,
                                                @PathVariable(value = "id") long workerId);

    @PutMapping(value = "/updateSafetyWorker")
    ResponseEntity<Object> updateSafetyWorkerById(@RequestHeader(value = "user_id") int userId,
                                                  @RequestHeader(value = "token") String token,
                                                  @RequestBody Object request);

    // Unit master controller

    @GetMapping(value = "/unitMaster")
    ResponseEntity<List<Object>> getAllUnitMaster(@RequestHeader(value = "userId") Integer userId,
                                                  @RequestHeader(value = "token") String token);

    @GetMapping(value = "/unitMaster/{id}")
    ResponseEntity<Object> getUnitMasterById(@RequestHeader(value = "userId") Integer userId,
                                             @RequestHeader(value = "token") String token,
                                             @PathVariable(value = "id") long unitMasterId);


}
