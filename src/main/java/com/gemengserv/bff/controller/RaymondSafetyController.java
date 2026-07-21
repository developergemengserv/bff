package com.gemengserv.bff.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gemengserv.bff.service.RaymondSafetyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/raymondsafety")
public class RaymondSafetyController
{
    @Autowired
    private RaymondSafetyService raymondSafetyService;

    // Activity controller

    @RequestMapping(value = "/rest/v1/activity/master", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getActivitiesByUser(
            @RequestParam("user_id") int userId,
            @RequestParam("project_id") int projectId,
            @RequestParam("token") String token,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return raymondSafetyService.getActivitiesByUser(userId,projectId,token,pageSize,page,lastSync);
    }

    @RequestMapping(value = "/rest/v1/activity/checklist/master", method = RequestMethod.GET)
    public ResponseEntity<LinkedHashMap<String, Object>> getChecklists(
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam("user_id") int user_id, @RequestParam("project_id") int project_id,
            @RequestParam("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return raymondSafetyService.getChecklists(page,pageSize,user_id,project_id,token,lastSync);
    }

    // Activity Type Of Work Mapping Controller

    @GetMapping(value = "/rest/v1/activityTypeOfWork", produces = "application/json")
    ResponseEntity<Object> getActivityTypeOfWorkMapping(@RequestHeader("user_id") int userId,
                                                        @RequestHeader("token") String token,
                                                        @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return raymondSafetyService.getActivityTypeOfWorkMapping(userId,token,lastSync);
    }

    // Activity Unit Mapping Controller

    @GetMapping(value = "activityUnitMapping")
    List<Object> getAllActivityUnitMapping()
    {
        return raymondSafetyService.getAllActivityUnitMapping();
    }

    // Common Controller

    @RequestMapping(value = "/rest/v1/configuration", method = RequestMethod.GET)
    ResponseEntity<Object> getConfiguration(@RequestParam("package_id") String package_id)
    {
        return raymondSafetyService.getConfiguration(package_id);
    }

    @RequestMapping(value = "/rest/v1/login", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username,
                                              @RequestParam("password") String password) throws Exception
    {
        return raymondSafetyService.login(username, password);
    }

    @RequestMapping(value = "/rest/v1/checkotp", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> checkotp(@RequestParam("otp") int otp,
                                                 @RequestParam("user_id") int user_id,
                                                 @RequestParam("version") String version,
                                                 @RequestParam("device_code") String device_code)
    {
        return raymondSafetyService.checkotp(otp, user_id, version, device_code);
    }

    @RequestMapping(value = "/rest/v1/logout", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> logout(@RequestParam("user_id") int user_id,
                                               @RequestParam("version") String version,
                                               @RequestParam("device_code") String device_code)
    {
        return raymondSafetyService.logout(user_id, version, device_code);
    }

    @RequestMapping(value = "/rest/v1/userDetails", method = RequestMethod.GET)
    ResponseEntity<?> userDetails(@RequestParam("user_id") int user_id,
                                  @RequestParam("token") String token)
    {
        return raymondSafetyService.userDetails(user_id, token);
    }

    @RequestMapping(value = "/rest/v1/media/find", method = RequestMethod.GET)
    void doDownload(@RequestParam("media_url") String mediaUrl,
                    @RequestParam("user_id") int user_id,
                    @RequestParam("token") String token,
                    HttpServletResponse response) throws IOException
    {
        raymondSafetyService.doDownload(mediaUrl, user_id, token, response);
    }

    @PostMapping(value = "/rest/v1/upload/signature", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadSignature(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestPart(value = "file") MultipartFile file)
    {
        return raymondSafetyService.uploadSignature(userId, token, file);
    }

    @PostMapping("/rest/v2/login")
    ResponseEntity<Object> loginAPI(@RequestParam("username") String username,
                                    @RequestParam("password") String password)
    {
        return raymondSafetyService.loginAPI(username, password);
    }

    @PostMapping("/rest/v1/users/userinfo")
    ResponseEntity<Object> addDeviceTokenAndAppVersion(@RequestHeader("user_id") int userId,
                                                       @RequestHeader("token") String token,
                                                       @RequestParam("deviceToken") String deviceToken,
                                                       @RequestParam(value = "appVersion", defaultValue = "1", required = false) String appVersion)
    {
        return raymondSafetyService.addDeviceTokenAndAppVersion(userId, token, deviceToken, appVersion);
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Object> registerUser(@RequestBody Object userRegisterRequest)
    {
        return raymondSafetyService.registerUser(userRegisterRequest);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = {"application/json"})
    ResponseEntity<Object> registerUser(@RequestParam(value = "user_id") Integer userId)
    {
        return raymondSafetyService.registerUser(userId);
    }

    // Emergency HelpLine Controller

    @GetMapping(value = "/getEmergencyHelpline")
    ResponseEntity<List<Object>> getAllEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
                                                         @RequestHeader(value = "token") String token,
                                                         int projectId)
    {
        return raymondSafetyService.getAllEmergencyHelpLine(userId, token, projectId);
    }

    // Hazards Controller

    @PostMapping(value = "/hazards/upload/{projectId}", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadHazards(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @PathVariable(value = "projectId") Integer projectId,
                                         @RequestPart(value = "file") MultipartFile file)
    {
        return raymondSafetyService.uploadHazards(userId, token, projectId, file);
    }

    @GetMapping(value = "/getHazardsByProjectId/{project_id}")
    ResponseEntity<List<Object>> getHazardsByProjectId(@RequestHeader(value = "user_id") Integer userId,
                                                       @RequestHeader(value = "token") String token,
                                                       @PathVariable(value = "project_id") int projectId)
    {
        return raymondSafetyService.getHazardsByProjectId(userId, token, projectId);
    }

    // Location Master Controller

    @RequestMapping(value = "/location/db/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> findLocation(@RequestBody String data,
                                                     @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return raymondSafetyService.findLocation(data, lastSync);
    }

    @RequestMapping(value = "/rest/v1/location/db/findall", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllLocationsFromDB(@RequestParam(value = "project_id", required = true) int pid,
                                                              @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
                                                              @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                              @RequestParam("user_id") int user_id,
                                                              @RequestParam("token") String token)
    {
        return raymondSafetyService.getAllLocationsFromDB(pid,page,pageSize,user_id,token);
    }

// Observation Master Controller

    @RequestMapping(value = "/rest/v1/observation/master/db/findall", method = RequestMethod.GET)
    ResponseEntity<Object> getAllObservationsFromDB(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
                                                    @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                    @RequestHeader(value = "userId") Integer userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return raymondSafetyService.getAllObservationsFromDB(page, pageSize, userId, token, lastSync);
    }

    // Progress Report Controller

    @PostMapping(value = "/progressReport")
    ResponseEntity<Object> saveProgressReport(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestBody Object progressReportRequest)
    {
        return raymondSafetyService.saveProgressReport(userId, token, progressReportRequest);
    }

    @GetMapping(value = "/progressReport")
    ResponseEntity<List<Object>> getProgressReport(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token)
    {
        return raymondSafetyService.getProgressReport(userId, token);
    }

    @GetMapping(value = "/progressReport/{id}")
    ResponseEntity<Object> getProgressReportById(@RequestHeader(value = "userId") Integer userId,
                                                 @RequestHeader(value = "token") String token,
                                                 @PathVariable(value = "id") long progressReportId)
    {
        return raymondSafetyService.getProgressReportById(userId, token, progressReportId);
    }

    // Project Controller

    @RequestMapping(value = "/rest/v1/projects", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> restProjects(@RequestParam("user_id") int user_id,
                                                     @RequestParam("token") String token,
                                                     @RequestParam(value = "lastSync", required = false) String lastSync,
                                                     @RequestParam(value = "zoneId", required = false, defaultValue = "0") int zoneId,
                                                     @RequestParam(value = "fundId", required = false, defaultValue = "0") int fundId) throws JsonParseException, JsonMappingException, IOException
    {
        return raymondSafetyService.restProjects(user_id, token, lastSync, zoneId, fundId);
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    ModelAndView projectById(@RequestParam("pid") int project_id)
    {
        return raymondSafetyService.projectById(project_id);
    }

    // Report Controller

    @RequestMapping(value = "/rorReport", method = RequestMethod.GET)
    void rorReport()
    {
        raymondSafetyService.rorReport();
    }

    @RequestMapping(value = "/ptwReport", method = RequestMethod.POST)
    ResponseEntity<Object> ptwReport(@RequestBody Object ptwReportRequest)
    {
        return raymondSafetyService.ptwReport(ptwReportRequest);
    }

    @RequestMapping(value = "/safetyObsReport", method = RequestMethod.GET)
    void safetyObsReport()
    {
        raymondSafetyService.safetyObsReport();
    }

    @RequestMapping(value = "/escalateOBSReport", method = RequestMethod.GET)
    void escalateOBSReport()
    {
        raymondSafetyService.escalateOBSReport();
    }

    @RequestMapping(value = "/safetyObsReportByFilter", method = RequestMethod.GET)
    ResponseEntity<Object> safetyObsReportByFilter(@RequestParam(value = "fromDate") String fromDate,
                                                   @RequestParam(value = "toDate") String toDate,
                                                   @RequestParam(value = "userId") Integer userId,
                                                   @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId)
    {
        return raymondSafetyService.safetyObsReportByFilter(fromDate, toDate, userId, projectId);
    }

    @RequestMapping(value = "/manPowerReport", method = RequestMethod.GET)
    ResponseEntity<Object> safetyManPowerReport(@RequestParam(value = "fromDate") String fromDate,
                                                @RequestParam(value = "toDate") String toDate,
                                                @RequestParam(value = "userId") Integer userId,
                                                @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId)
    {
        return raymondSafetyService.safetyManPowerReport(fromDate, toDate, userId, projectId);
    }

    @RequestMapping(value = "/safetyTbtExcelReport", method = RequestMethod.GET)
    ResponseEntity<Object> safetyTbtExcelReport(@RequestParam(value = "fromDate") String fromDate,
                                                @RequestParam(value = "toDate") String toDate,
                                                @RequestParam(value = "userId") Integer userId,
                                                @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId)
    {
        return raymondSafetyService.safetyTbtExcelReport(fromDate, toDate, userId, projectId);
    }

    @RequestMapping(value = "/safetyMeetingExcelReport", method = RequestMethod.GET)
    ResponseEntity<Object> safetyMeetingExcelReport(@RequestParam(value = "fromDate") String fromDate,
                                                    @RequestParam(value = "toDate") String toDate,
                                                    @RequestParam(value = "userId") Integer userId,
                                                    @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId)
    {
        return raymondSafetyService.safetyMeetingExcelReport(fromDate, toDate, userId, projectId);
    }

    @RequestMapping(value = "/safetyEqExcelReport", method = RequestMethod.GET)
    ResponseEntity<Object> safetyEqExcelReport(@RequestParam(value = "fromDate") String fromDate,
                                               @RequestParam(value = "toDate") String toDate,
                                               @RequestParam(value = "userId") Integer userId,
                                               @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId)
    {
        return raymondSafetyService.safetyEqExcelReport(fromDate, toDate, userId, projectId);
    }

    @RequestMapping(value = "/safetyPTWExcelReport", method = RequestMethod.GET)
    ResponseEntity<Object> safetyPTWExcelReport(@RequestParam(value = "fromDate") String fromDate,
                                                @RequestParam(value = "toDate") String toDate,
                                                @RequestParam(value = "userId") Integer userId,
                                                @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId)
    {
        return raymondSafetyService.safetyPTWExcelReport(fromDate, toDate, userId, projectId);
    }

    @GetMapping("/downloadMemoPdf")
    ResponseEntity<Object> downloadMemoPdf(@RequestHeader("user_id") int user_id,
                                           @RequestHeader("token") String token,
                                           @RequestParam(name = "obsId", required = false, defaultValue = "0") int obsId)
    {
        return raymondSafetyService.downloadMemoPdf(user_id, token, obsId);
    }

    @GetMapping("/downloadOBSPdf")
    ResponseEntity<Object> downloadOBSPdf(@RequestHeader("user_id") int user_id,
                                          @RequestHeader("token") String token,
                                          @RequestParam(name = "obsId", required = false, defaultValue = "0") int obsId)
    {
        return raymondSafetyService.downloadOBSPdf(user_id, token, obsId);
    }

    @GetMapping("/downloadIncidentPdf")
    ResponseEntity<Object> downloadIncidentPdf(@RequestHeader("user_id") int user_id,
                                               @RequestHeader("token") String token,
                                               @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId)
    {
        return raymondSafetyService.downloadIncidentPdf(user_id, token, incidentId);
    }

    @GetMapping("/sendIncidentPdf")
    ResponseEntity<Object> sendIncidentPdf(@RequestHeader("user_id") int user_id,
                                           @RequestHeader("token") String token,
                                           @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId)
    {
        return raymondSafetyService.sendIncidentPdf(user_id, token, incidentId);
    }

    @GetMapping("/downloadTBTPdf")
    ResponseEntity<Object> downloadTBTPdf(@RequestHeader("user_id") int user_id,
                                          @RequestHeader("token") String token,
                                          @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId,
                                          @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType)
    {
        return raymondSafetyService.downloadTBTPdf(user_id, token, tbtId, requestType);
    }

    @GetMapping("/downloadGPPdf")
    ResponseEntity<Object> downloadGPPdf(@RequestHeader("user_id") int user_id,
                                         @RequestHeader("token") String token,
                                         @RequestParam(name = "goodPracticeId", required = true, defaultValue = "0") int goodPracticeId)
    {
        return raymondSafetyService.downloadGPPdf(user_id, token, goodPracticeId);
    }

    @RequestMapping(value = "/equipmentReport", method = RequestMethod.POST)
    ResponseEntity<Object> equipmentReport(@RequestBody Object  equipmentReportRequest)
    {
        return raymondSafetyService.equipmentReport(equipmentReportRequest);
    }

    @RequestMapping(value = "/downloadInspectionPdf", method = RequestMethod.GET)
    ResponseEntity<Object> inspectionReport(@RequestHeader("user_id") int user_id,
                                            @RequestHeader("token") String token,
                                            @RequestParam(name = "insId", required = true, defaultValue = "0") int insId)
    {
        return raymondSafetyService.inspectionReport(user_id, token, insId);
    }

    @RequestMapping(value = "/tbtReportPdf", method = RequestMethod.GET)
    ResponseEntity<Object> tbtReport(@RequestHeader("user_id") int user_id,
                                     @RequestHeader("token") String token,
                                     @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId,
                                     @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType)
    {
        return raymondSafetyService.tbtReport(user_id, token, tbtId, requestType);
    }

    @RequestMapping(value = "/meetingReportPdf", method = RequestMethod.GET)
    ResponseEntity<Object> meetingReport(@RequestHeader("user_id") int user_id,
                                         @RequestHeader("token") String token,
                                         @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId,
                                         @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType)
    {
        return raymondSafetyService.meetingReport(user_id, token, tbtId, requestType);
    }

    @RequestMapping(value = "/downloadWPPdf", method = RequestMethod.GET)
    ResponseEntity<Object> ewpReport(@RequestHeader("user_id") int user_id,
                                     @RequestHeader("token") String token,
                                     @RequestParam(name = "wpId", required = true, defaultValue = "0") int wpId,
                                     @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType)
    {
        return raymondSafetyService.ewpReport(user_id, token, wpId, requestType);
    }

    @RequestMapping(value = "/firstAidReportPdf", method = RequestMethod.GET)
    ResponseEntity<Object> firstAidReport(@RequestHeader("user_id") int user_id,
                                          @RequestHeader("token") String token,
                                          @RequestParam(name = "firstAidId", required = true, defaultValue = "0") int firstAidId,
                                          @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType)
    {
        return raymondSafetyService.firstAidReport(user_id, token, firstAidId, requestType);
    }

    @RequestMapping(value = "/nearMissReportPdf", method = RequestMethod.GET)
    ResponseEntity<Object> nearMissReport(@RequestHeader("user_id") int user_id,
                                          @RequestHeader("token") String token,
                                          @RequestParam(name = "nearMissId", required = true, defaultValue = "0") int nearMissId,
                                          @RequestParam(name = "requestType", required = false, defaultValue = "Mobile") String requestType)
    {
        return raymondSafetyService.nearMissReport(user_id, token, nearMissId, requestType);
    }

    @GetMapping("/downloadWorkerPdf")
    ResponseEntity<Object> downloadWorkerPdf(@RequestHeader("user_id") int user_id,
                                             @RequestHeader("token") String token,
                                             @RequestParam(name = "workerId", required = true, defaultValue = "0") int workerId,
                                             @RequestParam(name = "requestType", required = true, defaultValue = "Mobile") String requestType)
    {
        return raymondSafetyService.downloadWorkerPdf(user_id, token, workerId, requestType);
    }

    // Safe Man Hours Controller

    @PostMapping(value = "/safety/safeManHrs")
    ResponseEntity<Object> saveManHrs(@RequestHeader(value = "userId") Integer userId,
                                      @RequestHeader(value = "token") String token,
                                      @RequestBody Object manHrsRequest)
    {
        return raymondSafetyService.saveManHrs(userId, token, manHrsRequest);
    }

    @GetMapping(value = "/safety/safeManHrs")
    ResponseEntity<List<Object>> getManHrs(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token)
    {
        return raymondSafetyService.getManHrs(userId, token);
    }

    @GetMapping(value = "/safety/getManPower")
    ResponseEntity<Object> getManPower(@RequestParam (value = "projectId") Integer projectId)
    {
        return raymondSafetyService.getManPower(projectId);
    }

    @GetMapping(value = "/safety/safeManHrsByProjectId")
    ResponseEntity<List<Object>> getManHrsByProjectId(@RequestHeader(value = "userId") Integer userId,
                                                      @RequestHeader(value = "token") String token,
                                                      @RequestParam(value = "project_id") Integer projectId)
    {
        return raymondSafetyService.getManHrsByProjectId(userId, token, projectId);
    }

    @GetMapping(value = "/safety/safeManHrsBySP")
    ResponseEntity<Object> getManHrsByProjectId(@RequestHeader(value = "userId") Integer userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestParam(value = "project_id") Integer projectId,
                                                @RequestParam(value = "page_num") int pageNum,
                                                @RequestParam(value = "page_size") int pageSize)
    {
        return raymondSafetyService.getManHrsByProjectId(userId, token, projectId, pageNum, pageSize);
    }

    @GetMapping(value = "/safety/safeManHrsById")
    ResponseEntity<Object> safeManHrsById(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestParam(value = "manHrsId") Integer manHrsId)
    {
        return raymondSafetyService.safeManHrsById(userId,token,manHrsId);
    }

    // Safety Controller

    @RequestMapping(value = "/safety/obs/create", method = RequestMethod.POST)
    ResponseEntity<Object> createSafetyObservation(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @RequestBody Object obsRequest)
    {
        return raymondSafetyService.createSafetyObservation(userId, token, obsRequest);
    }

    @RequestMapping(value = "/safety/obs/update", method = RequestMethod.POST)
    ResponseEntity<Object> updateSafetyObservation(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @RequestBody Object updateRequest)
    {
        return raymondSafetyService.updateSafetyObservation(userId, token, updateRequest);
    }

    @RequestMapping(value = "/safety/obs/find", method = RequestMethod.POST)
    ResponseEntity<Object> findSafetyObservation(@RequestHeader(value = "userId") Integer userId,
                                                 @RequestHeader(value = "token") String token,
                                                 @RequestParam(value = "lastSync", required = false) String lastSync,
                                                 @RequestBody Object request)
    {
        return raymondSafetyService.findSafetyObservation(userId, token, lastSync, request);
    }

    @RequestMapping(value = "/safety/obs/find/{obsId}", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyObservationByObsId(@RequestHeader(value = "userId") Integer userId,
                                                        @RequestHeader(value = "token") String token,
                                                        @PathVariable(value = "obsId") Integer obsId)
    {
        return raymondSafetyService.findSafetyObservationByObsId(userId, token, obsId);
    }

    @RequestMapping(value = "/safety/obs/history/find", method = RequestMethod.POST)
    ResponseEntity<Object> findObservationRequestHistory(@RequestHeader(value = "userId") Integer userId,
                                                         @RequestHeader(value = "token") String token,
                                                         @RequestBody Object request)
    {
        return raymondSafetyService.findObservationRequestHistory(userId, token, request);
    }

    // MASTER API

    @RequestMapping(value = "/safety/master/unsafeAct/findAll", method = RequestMethod.GET)
    ResponseEntity<Object> findAllUnsafeAct(@RequestHeader(value = "userId") Integer userId,
                                            @RequestHeader(value = "token") String token,
                                            @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return raymondSafetyService.findAllUnsafeAct(userId, token, lastSync);
    }

    @RequestMapping(value = "/safety/master/unsafeCondition/findAll", method = RequestMethod.GET)
    ResponseEntity<Object> findAllUnsafeCondition(@RequestHeader(value = "userId") Integer userId,
                                                  @RequestHeader(value = "token") String token,
                                                  @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return raymondSafetyService.findAllUnsafeCondition(userId, token, lastSync);
    }

    @RequestMapping(value = "/safety/master/typeOfWork/findAll", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> findAllTypeOfWork(@RequestParam("user_id") int userId,
                                                          @RequestParam("token") String token,
                                                          @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return raymondSafetyService.findAllTypeOfWork(userId, token, lastSync);
    }

    // FOR NCR SPECIFIC API

    @RequestMapping(value = "/safety/ncr/create", method = RequestMethod.POST)
    ResponseEntity<Object> createSafetyNCR(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object request)
    {
        return raymondSafetyService.createSafetyNCR(userId, token, request);
    }

    @RequestMapping(value = "/safety/ncr/update", method = RequestMethod.POST)
    ResponseEntity<Object> updateSafetyNcr(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object request)
    {
        return raymondSafetyService.updateSafetyNcr(userId, token, request);
    }

    @RequestMapping(value = "/safety/ncr/find", method = RequestMethod.POST)
    ResponseEntity<Object> findSafetyNCR(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @RequestParam(value = "lastSync", required = false) String lastSync,
                                         @RequestBody Object ncrRequest)
    {
        return raymondSafetyService.findSafetyNCR(userId, token, lastSync, ncrRequest);
    }

    @RequestMapping(value = "/safety/ncr/history/find", method = RequestMethod.POST)
    ResponseEntity<Object> findNCRHistory(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestBody Object ncrRequest)
    {
        return raymondSafetyService.findNCRHistory(userId, token, ncrRequest);
    }

    @RequestMapping(value = "/safety/ptw", method = RequestMethod.POST)
    ResponseEntity<Object> createSafetyPTW(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object ptwRequest)
    {
        return raymondSafetyService.createSafetyPTW(userId, token, ptwRequest);
    }

    @RequestMapping(value = "/safety/ptw", method = RequestMethod.PUT)
    ResponseEntity<Object> updateSafetyPTW(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object ptwRequest)
    {
        return raymondSafetyService.updateSafetyPTW(userId, token, ptwRequest);
    }

    @RequestMapping(value = "/safety/ptwStatus", method = RequestMethod.PUT)
    ResponseEntity<Object> updatePTWStatus(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestBody Object request)
    {
        return raymondSafetyService.updatePTWStatus(userId, token, request);
    }

    @RequestMapping(value = "/safety/ptwClose", method = RequestMethod.PUT)
    ResponseEntity<Object> updateUnclosedPTW()
    {
        return raymondSafetyService.updateUnclosedPTW();
    }

    @RequestMapping(value = "/safety/ptw/find", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyPTW(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                         @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                         @RequestParam(value = "project_id") Integer projectId,
                                         @RequestParam(value = "lastSync", required = false) String lastSync,
                                         @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id)
    {
        return raymondSafetyService.findSafetyPTW(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
    }

    @RequestMapping(value = "/safety/ptw/findMin", method = RequestMethod.GET)
    public ResponseEntity<Object> findPTW(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestParam(value = "project_id") int projectId,
                                          @RequestParam(value = "lastSync", required = false) String lastSync,
                                          @RequestParam(value = "locationLevel1Id", required = false, defaultValue = "0") int locationLevel1Id)
    {
        return raymondSafetyService.findPTW(userId, token, projectId, lastSync, locationLevel1Id);
    }

    @RequestMapping(value = "/safety/ptw/findsp", method = RequestMethod.GET)
    public ResponseEntity<Object> findSafetyPTWSP(@RequestHeader(value = "userId") Integer userId,
                                                  @RequestHeader(value = "token") String token,
                                                  @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                                  @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                                  @RequestParam(value = "project_id") Integer projectId)
    {
        return raymondSafetyService.findSafetyPTWSP(userId, token, page, pageSize, projectId);
    }

    @RequestMapping(value = "/safety/ptw/find/V2", method = RequestMethod.GET)
    public ResponseEntity<Object> findSafetyPTWW(@RequestHeader(value = "userId") Integer userId,
                                                 @RequestHeader(value = "token") String token,
                                                 @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                                 @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                                 @RequestParam(value = "project_id") Integer projectId,
                                                 @RequestParam(value = "lastSync", required = false) String lastSync,
                                                 @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id)
    {
        return raymondSafetyService.findSafetyPTWW(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
    }

    @RequestMapping(value = "/safety/ptw/find/{ptwId}", method = RequestMethod.GET)
    public ResponseEntity<Object> findSafetyPTWById(@RequestHeader(value = "userId") Integer userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @PathVariable(value = "ptwId") Integer ptwId)
    {
        return raymondSafetyService.findSafetyPTWById(userId, token, ptwId);
    }

    @RequestMapping(value = "/safety/ptw/findsp/{ptwId}", method = RequestMethod.GET)
    public ResponseEntity<Object> findSafetyPTWByIdSP(@RequestHeader(value = "userId") Integer userId,
                                                      @RequestHeader(value = "token") String token,
                                                      @PathVariable(value = "ptwId") Integer ptwId)
    {
        return raymondSafetyService.findSafetyPTWByIdSP(userId, token, ptwId);
    }

    @RequestMapping(value = "/safety/ptw/find/V2/{ptwId}", method = RequestMethod.GET)
    public ResponseEntity<Object> findPTWById(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @PathVariable(value = "ptwId") Integer ptwId)
    {
        return raymondSafetyService.findPTWById(userId, token, ptwId);
    }

    @RequestMapping(value = "/safety/findPtwByFilter", method = RequestMethod.GET)
    public ResponseEntity<Object> findPTWByFilter(@RequestHeader(value = "userId") Integer userId,
                                                  @RequestHeader(value = "token") String token,
                                                  @RequestParam(value = "projectId") Integer projectId,
                                                  @RequestParam(value = "fromDate") String fromDate,
                                                  @RequestParam(value = "toDate") String toDate)
    {
        return raymondSafetyService.findPTWByFilter(userId, token, projectId, fromDate, toDate);
    }

    @RequestMapping(value = "/safety/ptw/history/find", method = RequestMethod.POST)
    public ResponseEntity<Object> findPTWHistory(@RequestHeader(value = "userId") Integer userId,
                                                 @RequestHeader(value = "token") String token,
                                                 @RequestBody Object ptwHistoryFindRequest)
    {
        return raymondSafetyService.findPTWHistory(userId, token, ptwHistoryFindRequest);
    }

    @RequestMapping(value = "/safety/findPtwCount", method = RequestMethod.POST)
    public ResponseEntity<Object> findPtwCount(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestBody Object dashboardCountRequest)
    {
        return raymondSafetyService.findPtwCount(userId, token, dashboardCountRequest);
    }

    @RequestMapping(value = "/safety/findObsCount", method = RequestMethod.POST)
    public ResponseEntity<Object> findObsCount(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestBody Object dashboardCountRequest) {
        return raymondSafetyService.findObsCount(userId, token, dashboardCountRequest);
    }

    @RequestMapping(value = "/safety/findEcCount", method = RequestMethod.POST)
    public ResponseEntity<Object> findEcCount(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestBody Object dashboardCountRequest) {
        return raymondSafetyService.findEcCount(userId, token, dashboardCountRequest);
    }

    @RequestMapping(value = "/safety/findTbtCount", method = RequestMethod.POST)
    public ResponseEntity<Object> findTbtCount(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestBody Object dashboardCountRequest) {
        return raymondSafetyService.findTbtCount(userId, token, dashboardCountRequest);
    }

    @RequestMapping(value = "/safety/findManPowerCount", method = RequestMethod.POST)
    public ResponseEntity<Object> findManPowerCount(@RequestHeader(value = "userId") Integer userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestBody Object dashboardCountRequest) {
        return raymondSafetyService.findManPowerCount(userId, token, dashboardCountRequest);
    }

    @RequestMapping(value = "/safety/findCommitteeCount", method = RequestMethod.POST)
    public ResponseEntity<Object> findCommitteeCount(@RequestHeader(value = "userId") Integer userId,
                                                     @RequestHeader(value = "token") String token,
                                                     @RequestBody Object dashboardCountRequest) {
        return raymondSafetyService.findCommitteeCount(userId, token, dashboardCountRequest);
    }

    @PostMapping(value = "/safety/uploadMultipleFiles", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadMultipleFiles(@RequestHeader(value = "userId") Integer userId,
                                                      @RequestHeader(value = "token") String token,
                                                      @RequestParam("eventId") Integer eventId,
                                                      @RequestParam("eventName") String eventName,
                                                      @RequestPart("file") MultipartFile[] files) {
        return raymondSafetyService.uploadMultipleFiles(userId, token, eventId, eventName, files);
    }

    @PostMapping(value = "/safety/uploadCheckListMedia", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadCheckListMedia(@RequestHeader(value = "userId") Integer userId,
                                                       @RequestHeader(value = "token") String token,
                                                       @RequestParam("checklistAnswerId") Integer checklistAnswerId,
                                                       @RequestParam("file") MultipartFile[] files) {
        return raymondSafetyService.uploadCheckListMedia(userId, token, checklistAnswerId, files);
    }

    @RequestMapping(value = "/safety/findUsersByProjectIdAndRoleId", method = RequestMethod.GET)
    public ResponseEntity<Object> findUsersByProjectIdAndRoleId(@RequestHeader(value = "userId") Integer userId,
                                                                @RequestHeader(value = "token") String token,
                                                                @RequestParam(value = "project_id") Integer projectId,
                                                                @RequestParam(value = "roleId") Integer roleId,
                                                                @RequestParam(value = "lastSync", required = false) String lastSync) {
        return raymondSafetyService.findUsersByProjectIdAndRoleId(userId, token, projectId, roleId, lastSync);
    }

    @RequestMapping(value = "/safety/sendOBSNotification", method = RequestMethod.GET)
    public ResponseEntity<Object> sendOBSNotification(@RequestHeader(value = "level1LocationId") Integer level1LocationId,
                                                      @RequestHeader(value = "obsId") Integer obsId,
                                                      @RequestHeader(value = "user_id") Integer userId) {
        return raymondSafetyService.sendOBSNotification(level1LocationId, obsId, userId);
    }

    @RequestMapping(value = "/safety/master/answerType/findAll", method = RequestMethod.GET)
    public ResponseEntity<Object> findAllAnswerType(@RequestHeader(value = "userId") Integer userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestParam(value = "lastSync", required = false) String lastSync) {
        return raymondSafetyService.findAllAnswerType(userId, token, lastSync);
    }

    @RequestMapping(value = "/safety/master/checklistQuestions/findAll", method = RequestMethod.GET)
    public ResponseEntity<Object> findAllChecklistQuestions(@RequestHeader(value = "userId") Integer userId,
                                                            @RequestHeader(value = "token") String token,
                                                            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return raymondSafetyService.findAllChecklistQuestions(userId, token, lastSync);
    }

    @GetMapping(value = "/safety/master/typeOfWorkChecklistMapping")
    public ResponseEntity<Object> findAllTypeOfWorkChecklistMapping(@RequestHeader(value = "userId") Integer userId,
                                                                    @RequestHeader(value = "token") String token,
                                                                    @RequestParam(value = "lastSync", required = false) String lastSync) {
        return raymondSafetyService.findAllTypeOfWorkChecklistMapping(userId, token, lastSync);
    }

    //Equipment

    @RequestMapping(value = "/safety/equipment", method = RequestMethod.POST)
    public ResponseEntity<Object> saveEquipment(@RequestHeader(value = "userId") Integer userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestBody Object equipmentRequest) {
        return raymondSafetyService.saveEquipment(userId, token, equipmentRequest);
    }

    @RequestMapping(value = "/safety/equipment", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateEquipment(@RequestHeader(value = "userId") Integer userId,
                                                  @RequestHeader(value = "token") String token,
                                                  @RequestBody Object equipmentRequest) {
        return raymondSafetyService.updateEquipment(userId, token, equipmentRequest);
    }

    @RequestMapping(value = "/safety/equipment/find_old", method = RequestMethod.GET)
    public ResponseEntity<Object> findSafetyEquipment(@RequestHeader(value = "userId") Integer userId,
                                                      @RequestHeader(value = "token") String token,
                                                      @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                                      @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                                      @RequestParam(value = "project_id") Integer projectId,
                                                      @RequestParam(value = "lastSync", required = false) String lastSync,
                                                      @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id) {
        return raymondSafetyService.findSafetyEquipment(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
    }

    @RequestMapping(value = "/safety/equipment/find", method = RequestMethod.GET)
    public ResponseEntity<Object> findSafetyEquipmentBySP(@RequestHeader(value = "userId") Integer userId,
                                                          @RequestHeader(value = "token") String token,
                                                          @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                                          @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                                          @RequestParam(value = "project_id") Integer projectId,
                                                          @RequestParam(value = "lastSync", required = false) String lastSync,
                                                          @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id) {
        return raymondSafetyService.findSafetyEquipmentBySP(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
    }

    @RequestMapping(value = "/safety/equipment/history/find", method = RequestMethod.POST)
    public ResponseEntity<Object> findEQHistory(@RequestHeader(value = "userId") Integer userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestBody Object eqHistoryFindRequest) {
        return raymondSafetyService.findEQHistory(userId, token, eqHistoryFindRequest);
    }

    @RequestMapping(value = "/safety/equipment/find/{equipmentId}", method = RequestMethod.GET)
    public ResponseEntity<Object> findSafetyEquipmentById(@RequestHeader(value = "userId") Integer userId,
                                                          @RequestHeader(value = "token") String token,
                                                          @PathVariable(value = "equipmentId") Integer equipmentId) {
        return raymondSafetyService.findSafetyEquipmentById(userId, token, equipmentId);
    }

    @RequestMapping(value = "/safety/findOBSByFilter", method = RequestMethod.GET)
    public ResponseEntity<Object> findOBSByFilter(@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate, @RequestParam(value = "userId") Integer userId, @RequestHeader(value = "token") String token) {
        return raymondSafetyService.findOBSByFilter(fromDate, toDate, userId, token);
    }

    @RequestMapping(value = "/safety/statistics/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<Object> findSafetyStatistics(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @PathVariable(value = "projectId") Integer projectId) {
        return raymondSafetyService.findSafetyStatistics(userId, token, projectId);
    }

    @RequestMapping(value = "/safety/bulletin", method = RequestMethod.POST)
    public ResponseEntity<Object> sendBulletin(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestBody Object bulletinRequest) {
        return raymondSafetyService.sendBulletin(userId, token, bulletinRequest);
    }

    @RequestMapping(value = "/safety/bulletin/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<Object> findBulletinByProjectId(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @PathVariable(value = "projectId") Integer projectId) {
        return raymondSafetyService.findBulletinByProjectId(userId, token, projectId);
    }

    @RequestMapping(value = "/safety/getSafetyDigitalLibrary", method = RequestMethod.GET)
    public ResponseEntity<Object> getSafetyDigitalLibrary(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestParam(value = "project_id") int projectId) {
        return raymondSafetyService.getSafetyDigitalLibrary(userId, token, projectId);
    }

    @RequestMapping(value = "/safety/ptw/getPendingPtw", method = RequestMethod.GET)
    public ResponseEntity<Object> getPendingPtw(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestParam int projectId) {
        return raymondSafetyService.getPendingPtw(userId, token, projectId);
    }

    @PostMapping(value = "/safety/safetyGoodPractices")
    public ResponseEntity<Object> saveGoodPractices(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestBody Object goodPracticesRequest) {
        return raymondSafetyService.saveGoodPractices(userId, token, goodPracticesRequest);
    }

    @GetMapping(value = "/safety/safetyGoodPractices")
    ResponseEntity<Object> getGoodPractices(@RequestHeader(value = "userId") Integer userId,
                                            @RequestHeader(value = "token") String token)
    {
        return raymondSafetyService.getGoodPractices(userId, token);
    }

    @GetMapping(value = "/safety/zones")
    public ResponseEntity<Object> getZoindnes(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token) {
        return raymondSafetyService.getZones(userId, token);
    }

    @RequestMapping(value = "/safety/workers/history/find", method = RequestMethod.POST)
    public ResponseEntity<Object> findWorkerRequestHistory(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestBody Object request) {
        return raymondSafetyService.findWorkerRequestHistory(userId, token, request);
    }

    // Safety Incident Controller

    @RequestMapping(value = "/safety/incident", method = RequestMethod.POST)
    public ResponseEntity<Object> saveIncident(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestBody Object request) {
        return raymondSafetyService.saveIncident(userId, token, request);
    }

    @RequestMapping(value = "/safety/incident", method = RequestMethod.GET)
    public ResponseEntity<Object> findSafetyIncident(@RequestHeader(value = "userId") Integer userId,
                                                     @RequestHeader(value = "token") String token,
                                                     @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                                     @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                                     @RequestParam(value = "project_id") Integer projectId) {
        return raymondSafetyService.findSafetyIncident(userId, token, page, pageSize, projectId);
    }

    @RequestMapping(value = "/safety/incident/{incidentId}", method = RequestMethod.GET)
    public ResponseEntity<Object> findIncidentById(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @PathVariable(value = "incidentId") Integer incidentId) {
        return raymondSafetyService.findIncidentById(userId, token, incidentId);
    }


    // Near Miss APIs

    @RequestMapping(value = "/addNearMiss", method = RequestMethod.POST)
    public ResponseEntity<Object> saveSafetyNearMiss(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestBody Object safetyNearMissRequest) {
        return raymondSafetyService.saveSafetyNearMiss(userId, token, safetyNearMissRequest);
    }

    @RequestMapping(value = "/getNearMiss", method = RequestMethod.GET)
    public ResponseEntity<Object> getNearMiss(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestParam(value = "projectId") Integer projectId) {
        return raymondSafetyService.getNearMiss(userId, token, projectId);
    }

    @RequestMapping(value = "/getNearMissById", method = RequestMethod.GET)
    public ResponseEntity<Object> getNearMissById(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestParam(value = "nearMissId") Integer nearMissId) {
        return raymondSafetyService.getNearMissById(userId, token, nearMissId);
    }

    @PutMapping(value = "/updateNearMiss")
    public ResponseEntity<Object> updateNearMissById(@RequestHeader(value = "userId") int userId, @RequestHeader(value = "token") String token, @RequestBody Object request) {
        return raymondSafetyService.updateNearMissById(userId, token, request);
    }

    @RequestMapping(value = "/getNearMissHistory", method = RequestMethod.GET)
    public ResponseEntity<Object> getNearMissHistory(@RequestHeader(value = "userId") Integer userId,
                                                     @RequestHeader(value = "token") String token,
                                                     @RequestParam(value = "nearMissId") Integer nearMissId)
    {
        return raymondSafetyService.getNearMissHistory(userId, token, nearMissId);
    }

    @RequestMapping(value = "/getNearMissAPHistory", method = RequestMethod.GET)
    ResponseEntity<Object> getNearMissAPHistory(@RequestHeader(value = "userId") Integer userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestParam(value = "nearMissId") Integer nearMissId)
    {
        return raymondSafetyService.getNearMissAPHistory(userId, token, nearMissId);
    }


    // First Aid APIs

    @RequestMapping(value = "/addFirstAid", method = RequestMethod.POST)
    public ResponseEntity<Object> saveSafetyFirstAid(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestBody Object safetyFirstAidRequest) {
        return raymondSafetyService.saveSafetyFirstAid(userId, token, safetyFirstAidRequest);
    }

    @RequestMapping(value = "/getFirstAid", method = RequestMethod.GET)
    public ResponseEntity<Object> getFirstAid(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestParam(value = "projectId") Integer projectId) {
        return raymondSafetyService.getFirstAid(userId, token, projectId);
    }

    @PutMapping(value = "/updateFirstAid")
    public ResponseEntity<Object> updateFirstAidById(@RequestHeader(value = "userId") int userId, @RequestHeader(value = "token") String token, @RequestBody Object updateFirstAidRequest) {
        return raymondSafetyService.updateFirstAidById(userId, token, updateFirstAidRequest);
    }

    @RequestMapping(value = "/getFirstAidById", method = RequestMethod.GET)
    ResponseEntity<Object> getFirstAidById(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestParam(value = "firstAidId") Integer firstAidId)
    {
        return raymondSafetyService.getFirstAidById(userId, token, firstAidId);
    }

    @RequestMapping(value = "/getFirstAidHistory", method = RequestMethod.GET)
    ResponseEntity<Object> getFirstAidHistory(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestParam(value = "firstAidId") Integer firstAidId)
    {
        return raymondSafetyService.getFirstAidHistory(userId, token, firstAidId);
    }

    @RequestMapping(value = "/getFirstAidAPHistory", method = RequestMethod.GET)
    ResponseEntity<Object> getFirstAidAPHistory(@RequestHeader(value = "userId") Integer userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestParam(value = "firstAidId") Integer firstAidId)
    {
        return raymondSafetyService.getFirstAidAPHistory(userId, token, firstAidId);
    }

    // Safety TBT Controller

    @PostMapping(value = "/SafetyTBT")
    public ResponseEntity<Object> saveSafetyTBT(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestBody Object safetyTBTRequest) {
        return raymondSafetyService.saveSafetyTBT(userId, token, safetyTBTRequest);
    }

    @GetMapping(value = "/SafetyTBT")
    public ResponseEntity<Object> getSafetyTBT(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId) {
        return raymondSafetyService.getSafetyTBT(userId, token, projectId);
    }

    @GetMapping(value = "/SafetyTBTSP")
    public ResponseEntity<Object> getSafetyTBTSP(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId)
    {
        return raymondSafetyService.getSafetyTBTSP(userId, token, projectId);
    }

    @GetMapping(value = "/SafetyTBTTopics")
    public ResponseEntity<Object> getSafetyTBTTopics(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token)
    {
        return raymondSafetyService.getSafetyTBTTopics(userId,token);
    }

    @GetMapping(value = "/SafetyTBTSPWithPageWise")
    public ResponseEntity<Object> SafetyTBTSPWithPageWise(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId, @RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNumber, @RequestParam(value = "pageSize", required = false, defaultValue = "0") Integer pageSize) {
        return raymondSafetyService.SafetyTBTSPWithPageWise(userId, token, projectId, pageNumber, pageSize);
    }

    @GetMapping(value = "/getSafetyTbtById/{id}")
    public ResponseEntity<Object> getSafetyTbtById(@RequestHeader(value = "userId") Integer userId, @RequestHeader(value = "token") String token, @PathVariable(value = "id") Integer tbtId) {
        return raymondSafetyService.getSafetyTbtById(userId, token, tbtId);
    }

    @PutMapping(value = "/updateSafetyTBT")
    public ResponseEntity<Object> updateSafetyTBT(@RequestHeader(value = "user_id") int userId, @RequestHeader(value = "token") String token, @RequestBody Object request) {
        return raymondSafetyService.updateSafetyTBT(userId, token, request);
    }

    @GetMapping(value = "/getSafetyTbtByIdWithSP")
    public ResponseEntity<Object> getSafetyTbtByIdWithSP(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "tbtId") Integer tbtId)
    {
        return raymondSafetyService.getSafetyTbtByIdWithSP(userId, token, tbtId);
    }

    @GetMapping(value = "/getSafetyTbtHistoryById/{id}")
    public ResponseEntity<Object> getSafetyTbtHistoryById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "id") Integer tbtId)
    {
        return raymondSafetyService.getSafetyTbtHistoryById(userId, token, tbtId);
    }

    @GetMapping(value = "/SafetyTBTMaster")
    public ResponseEntity<Object> getSafetyTBTMaster(@RequestHeader(value = "userId") Integer userId,
                                                     @RequestHeader(value = "token") String token)
    {
        return raymondSafetyService.getSafetyTBTMaster(userId, token);
    }

    // Safety Workers Controller

    @PostMapping(value = "/SafetyWorkers")
    public ResponseEntity<Object> saveSafetyWorkers(@RequestHeader(value = "user_id") int userId, @RequestHeader(value = "token") String token, @RequestBody Object safetyWorkersRequest) {
        return raymondSafetyService.saveSafetyWorkers(userId, token, safetyWorkersRequest);
    }

    @GetMapping(value = "/SafetyWorkers")
    public ResponseEntity<Object> getSafetyWorkers(@RequestHeader(value = "user_id") int userId, @RequestHeader(value = "token") String token, @RequestParam(value = "project_id") long projectId, @RequestParam(value = "company_id", required = false, defaultValue = "0") long companyId) {
        return raymondSafetyService.getSafetyWorkers(userId, token, projectId, companyId);
    }

    @GetMapping(value = "/SafetyWorkersByPagination")
    public ResponseEntity<Object> getSafetyWorkersWithPagination(@RequestHeader(value = "user_id") int userId, @RequestHeader(value = "token") String token, @RequestParam(value = "project_id") long projectId, @RequestParam(value = "company_id", required = false, defaultValue = "0") long companyId, @RequestParam(value = "page_num") int pageNum, @RequestParam(value = "page_size") int pageSize) {
        return raymondSafetyService.getSafetyWorkersWithPagination(userId, token, projectId, companyId, pageNum, pageSize);
    }

    @GetMapping(value = "/getApprovedWorkers")
    public ResponseEntity<Object> getSafetyWorkersForSelection(@RequestHeader(value = "user_id") int userId, @RequestHeader(value = "token") String token, @RequestParam(value = "project_id") long projectId, @RequestParam(value = "company_id", required = false, defaultValue = "0") long companyId) {
        return raymondSafetyService.getSafetyWorkersForSelection(userId, token, projectId, companyId);
    }

    @GetMapping(value = "/SafetyWorkers/{id}")
    public ResponseEntity<Object> getSafetyWorkersById(@RequestHeader(value = "user_id") int userId, @RequestHeader(value = "token") String token, @PathVariable(value = "id") long workerId) {
        return raymondSafetyService.getSafetyWorkersById(userId, token, workerId);
    }

    @PutMapping(value = "/updateSafetyWorker")
    public ResponseEntity<Object> updateSafetyWorkerById(@RequestHeader(value = "user_id") int userId, @RequestHeader(value = "token") String token, @RequestBody Object request) {
        return raymondSafetyService.updateSafetyWorkerById(userId, token, request);
    }

    @GetMapping(value = "/safety/getSafetyWorkerType")
    ResponseEntity<Object> getSafetyWorkerType(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token)
    {
        return raymondSafetyService.getSafetyWorkerType(userId, token);
    }

    // Unit master controller
    @GetMapping(value = "/unitMaster")
    ResponseEntity<List<Object>> getAllUnitMaster(@RequestHeader(value = "userId") Integer userId,
                                                  @RequestHeader(value = "token") String token)
    {
        return raymondSafetyService.getAllUnitMaster(userId, token);
    }

    @GetMapping(value = "/unitMaster/{id}")
    ResponseEntity<Object> getUnitMasterById(@RequestHeader(value = "userId") Integer userId,
                                             @RequestHeader(value = "token") String token,
                                             @PathVariable(value = "id") long unitMasterId)
    {
        return raymondSafetyService.getUnitMasterById(userId, token, unitMasterId);
    }
}
