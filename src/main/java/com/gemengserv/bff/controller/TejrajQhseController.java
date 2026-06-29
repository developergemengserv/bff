package com.gemengserv.bff.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gemengserv.bff.service.TejrajQhseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/tejraj")
public class TejrajQhseController
{

    @Autowired
    TejrajQhseService tejrajService;

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
        return tejrajService.getActivitiesByUser(userId, projectId, token, pageSize, page, lastSync);
    }

    @RequestMapping(value = "/rest/v1/activity/checklist/master", method = RequestMethod.GET)
    public ResponseEntity<LinkedHashMap<String, Object>> getChecklists(
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam("user_id") int user_id, @RequestParam("project_id") int project_id,
            @RequestParam("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return tejrajService.getChecklists(page, pageSize, user_id, project_id, token, lastSync);
    }

    @GetMapping(value = "/getActivities")
    public ResponseEntity<List<Object>> getActivities()
    {
        return tejrajService.getActivities();
    }

    @RequestMapping(value = "/addActivities", consumes = "multipart/form-data", method = RequestMethod.POST)
    public String addActivities(@RequestParam(value = "file") MultipartFile file,
                                @RequestParam(value = "userId") int userId)
    {
        return tejrajService.addActivities(file,userId);
    }

    @RequestMapping(value = "/addActivitiesChecklist", consumes = "multipart/form-data", method = RequestMethod.POST)
    public String addLocations(@RequestParam(value = "userId") int userId,
                               @RequestParam(value = "file") MultipartFile file)
    {
        return tejrajService.addLocations(userId,file);
    }

    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    public ResponseEntity<Object> addActivity(@RequestHeader(value = "userId") Integer userId,
                                                             @RequestHeader(value = "token") String token,
                                                             @RequestBody Object activityRequest)
    {
        return tejrajService.addActivity(userId, token, activityRequest);
    }

    @RequestMapping(value = "/editActivity", method = RequestMethod.POST)
    public ResponseEntity<Object> editActivity(@RequestHeader(value = "userId") Integer userId,
                                                              @RequestHeader(value = "token") String token,
                                                              @RequestBody Object activityRequest)
    {
        return tejrajService.editActivity(userId, token, activityRequest);
    }

    //    ActivityTypeOfWorkMappingController
    @GetMapping(value = "/rest/v1/activityTypeOfWork", produces = "application/json")
    public ResponseEntity<Object> getActivityTypeOfWorkMapping(@RequestHeader("user_id") int userId,
                                                               @RequestHeader("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return tejrajService.getActivityTypeOfWorkMapping(userId, token, lastSync);
    }

//   ActivityUnitMappingController
    @GetMapping(value = "activityUnitMapping")
    public List<Object> getAllActivityUnitMapping()
    {
        return tejrajService.getAllActivityUnitMapping();
    }

    //  CommonController
    @RequestMapping(value = "/rest/v1/configuration", method = RequestMethod.GET)
    public ResponseEntity<Object> getConfiguration(@RequestParam("package_id") String package_id) {
        return tejrajService.getConfiguration(package_id);
    }

    @RequestMapping(value = "/rest/v1/checkotp", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> checkotp(@RequestParam("otp") int otp, @RequestParam("user_id") int user_id, @RequestParam("version") String version,
                                                        @RequestParam("device_code") String device_code) {
        return tejrajService.checkotp(otp, user_id, version, device_code);
    }

    @RequestMapping(value = "/rest/v1/logout", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> logout(@RequestParam("user_id") int user_id, @RequestParam("version") String version,
                                                      @RequestParam("device_code") String device_code) {
        return tejrajService.logout(user_id, version, device_code);
    }

    @RequestMapping(value = "/rest/v1/userDetails", method = RequestMethod.GET)
    public ResponseEntity<Object> userDetails(@RequestParam("user_id") int user_id,
                                         @RequestParam("token") String token)
    {
        return tejrajService.userDetails(user_id, token);
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public Object addCompany(@RequestHeader("userId") int userId,
                      @RequestHeader("token") String token,
                      @RequestBody Object companyCreateRequest)
    {
        return tejrajService.addCompany(userId,token,companyCreateRequest);
    }

    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    public Object updateCompany(@RequestHeader("userId") int userId,
                                @RequestHeader("token") String token,
                                @RequestBody Object companyUpdateRequest)
    {
        return tejrajService.updateCompany(userId,token,companyUpdateRequest);
    }

    @RequestMapping(value = "/companyProjectWise", method = RequestMethod.GET)
    public List<Object> getCompaniesProjectWise(@RequestHeader("userId") int userId,
                                                 @RequestHeader("token") String token)
    {
        return tejrajService.getCompaniesProjectWise(userId, token);
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public List<Object> getCompanies(@RequestHeader("userId") int userId,
                                      @RequestHeader("token") String token) {
        return tejrajService.getCompanies(userId, token);
    }

    @RequestMapping(value = "/deleteCompany", method = RequestMethod.DELETE, produces = {"application/json"})
    public ResponseEntity<Object> deleteCompany(@RequestParam(value = "companyId") Integer companyId)
    {
        return tejrajService.deleteCompany(companyId);
    }

    @RequestMapping(value = "/downloadCompanies", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadCompaniesExcel(
            @RequestParam(value = "userId")   Integer userId,
            @RequestParam(value = "token")    String  token,
            @RequestParam(value = "pageNum",    defaultValue = "1")    int pageNum,
            @RequestParam(value = "pageSize",   defaultValue = "1000") int pageSize)
    {
        return tejrajService.downloadCompaniesExcel(userId, token, pageNum, pageSize);
    }

    @GetMapping("/registerUser")
    public Object registerUser(@RequestBody Object userRegisterRequest) {
        return tejrajService.registerUser(userRegisterRequest);
    }

    @PostMapping("/rest/v2/login")
    public ResponseEntity<Object> loginV2(@RequestParam("username") String username, @RequestParam("password") String password) {
        return tejrajService.loginAPI(username, password);
    }

    @RequestMapping(value = "/rest/v1/login", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        return tejrajService.login(username, password);
    }

    @RequestMapping(value = "/rest/v1/media/find", method = RequestMethod.GET)
    public void doDownload(@RequestParam("media_url") String mediaUrl, @RequestParam("user_id") int user_id, @RequestParam("token") String token, HttpServletResponse response)
            throws IOException
    {
        tejrajService.doDownload(mediaUrl,user_id,token,response);
    }

    @PostMapping(value = "/rest/v1/upload/signature", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadSignature(@RequestHeader(value = "userId") Integer userId,
                                                  @RequestHeader(value = "token") String token,
                                                  @RequestParam(value = "file") MultipartFile file)
    {
        return tejrajService.uploadSignature(userId,token,file);
    }

        // NCR API's

    @RequestMapping(value = "/qc/ncr", method = RequestMethod.POST)
    public ResponseEntity<Object> createQcNcr(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestBody Object ncrRequest)
    {
        return tejrajService.createQcNcr(userId, token,ncrRequest);
    }

    @RequestMapping(value = "/qc/ncr", method = RequestMethod.GET)
    public ResponseEntity<Object> getNcr( @RequestHeader(value = "userId") Integer userId,
                                     @RequestHeader(value = "token") String token,
                                     @RequestParam(value = "companyId") long companyId,
                                     @RequestParam(value = "projectId") int projectId,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "100") int pageSize)

    {
        return tejrajService.getNcr(userId, token,companyId,projectId,pageNum,pageSize);
    }

    @RequestMapping(value = "/qc/ncrDetails", method = RequestMethod.GET)
    public ResponseEntity<Object> getNcrDetails( @RequestHeader(value = "userId") Integer userId,
                                     @RequestHeader(value = "token") String token,
                                     @RequestParam(value = "ncrId") long ncrId)

    {
        return tejrajService.getNcrDetails(userId, token,ncrId);
    }

    @RequestMapping(value = "/ncr", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateNcr(@RequestHeader(value = "userId") int userId,
                                                         @RequestHeader(value = "token") String token,
                                                         @RequestBody Object qcNcrUpdateRequest)
    {
        return tejrajService.updateNcr(userId, token, qcNcrUpdateRequest);
    }

    @RequestMapping(value = "/filter/ncr", method = RequestMethod.POST)
    public ResponseEntity<Object> getNcrFilter(@RequestHeader(value = "userId") int userId,
                                                      @RequestHeader(value = "token") String token,
                                                      @RequestBody Object ncrFilterRequest)
    {
        return tejrajService.getNcrFilter(userId, token, ncrFilterRequest);
    }

    @RequestMapping(value = "/ncrReport", method = RequestMethod.GET)
    public ResponseEntity<Object> getDataForNcrReport(@RequestParam(value = "user_id") int userId,
                                                 @RequestParam(value = "token") String token,
                                                 @RequestParam(value = "ncrId",required = true) int ncrId,
                                                 @RequestParam(value = "webCall", defaultValue = "false", required = false) boolean webCall)
    {
        return tejrajService.getDataForNcrReport(userId, token, ncrId, webCall);
    }

    // Safety TBT API's

    @PostMapping(value = "/SafetyTBT")
    ResponseEntity<Object> saveSafetyTBT(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object safetyTBTRequest)
    {
        return tejrajService.saveSafetyTBT(userId,token,safetyTBTRequest);
    }

    @GetMapping(value = "/SafetyTBT")
    ResponseEntity<List<Object>> getSafetyTBT(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "projectId", required = false, defaultValue = "0") int projectId)
    {
        return tejrajService.getSafetyTBT(userId,token,projectId);
    }

    @GetMapping(value = "/getSafetyTbtById/{id}")
    ResponseEntity<Object> getSafetyTbtById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "id") Integer tbtId)
    {
        return tejrajService.getSafetyTbtById(userId,token,tbtId);
    }

    // safety controller API's

    // safety observation API's
    @PostMapping(value = "/safety/obs/create")
    public ResponseEntity<Object> createSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object obsRequest) {
        return tejrajService.createSafetyObservation(userId, token, obsRequest);
    }

    @PostMapping(value = "/safety/obs/update")
    public ResponseEntity<Object> updateSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object updateRequest) {
        return tejrajService.updateSafetyObservation(userId, token, updateRequest);
    }

    @PostMapping(value = "/safety/obs/find")
    public ResponseEntity<Object> findSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync,
            @RequestBody Object findRequest) {
        return tejrajService.findSafetyObservation(userId, token, lastSync, findRequest);
    }

    @GetMapping(value = "/safety/obs/find/{obsId}")
    public ResponseEntity<Object> findSafetyObservationByObsId(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "obsId") Integer obsId) {
        return tejrajService.findSafetyObservationByObsId(userId, token, obsId);
    }

    @PostMapping(value = "/safety/obs/history/find")
    public ResponseEntity<Object> findObservationRequestHistory(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object historyRequest) {
        return tejrajService.findObservationRequestHistory(userId, token, historyRequest);
    }

    // MASTER API

    @GetMapping(value = "/safety/master/unsafeAct/findAll")
    public ResponseEntity<Object> findAllUnsafeAct(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return tejrajService.findAllUnsafeAct(userId, token, lastSync);
    }

    @GetMapping(value = "/safety/master/unsafeCondition/findAll")
    public ResponseEntity<Object> findAllUnsafeCondition(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return tejrajService.findAllUnsafeCondition(userId, token, lastSync);
    }

    @GetMapping(value = "/safety/master/typeOfWork/findAll")
    public ResponseEntity<Object> findAllTypeOfWork(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return tejrajService.findAllTypeOfWork(userId, token, lastSync);
    }

    @GetMapping(value = "/safety/master/checklistQuestions/findAll")
    public ResponseEntity<Object> findAllChecklistQuestions(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return tejrajService.findAllChecklistQuestions(userId, token, lastSync);
    }

    @GetMapping(value = "/safety/master/typeOfWorkChecklistMapping")
    public ResponseEntity<Object> findAllTypeOfWorkChecklistMapping(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return tejrajService.findAllTypeOfWorkChecklistMapping(userId, token, lastSync);
    }

    @RequestMapping(value = "/rest/v1/getRoleMaster", method = RequestMethod.GET)
    ResponseEntity<Object> getRoleMaster()
    {
        return tejrajService.getRoleMaster();
    }

    // FOR PTW API

    @PostMapping(value = "/safety/ptw")
    public ResponseEntity<Object> createSafetyPTW(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ptwRequest) {
        return tejrajService.createSafetyPTW(userId, token, ptwRequest);
    }

    @PutMapping(value = "/safety/ptw")
    public ResponseEntity<Object> updateSafetyPTW(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ptwRequest) {
        return tejrajService.updateSafetyPTW(userId, token, ptwRequest);
    }

    @PutMapping(value = "/safety/ptwStatus")
    public ResponseEntity<Object> updatePTWStatus(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object request) {
        return tejrajService.updatePTWStatus(userId, token, request);
    }

    @PutMapping(value = "/safety/ptwClose")
    public ResponseEntity<Object> updateUnclosedPTW() {
        return tejrajService.updateUnclosedPTW();
    }

    @GetMapping(value = "/safety/ptw/find")
    public ResponseEntity<Object> findSafetyPTW(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "page_num", defaultValue = "1") Integer page,
            @RequestParam(value = "page_size", defaultValue = "500") Integer pageSize,
            @RequestParam(value = "project_id") Integer projectId,
            @RequestParam(value = "lastSync", required = false) String lastSync,
            @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id) {
        return tejrajService.findSafetyPTW(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
    }

    @GetMapping(value = "/safety/ptw/findsp")
    public ResponseEntity<Object> findSafetyPTWSP(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "page_num", defaultValue = "1") Integer page,
            @RequestParam(value = "page_size", defaultValue = "500") Integer pageSize,
            @RequestParam(value = "project_id") Integer projectId) {
        return tejrajService.findSafetyPTWSP(userId, token, page, pageSize, projectId);
    }

    @GetMapping(value = "/safety/ptw/find/V2")
    public ResponseEntity<Object> findSafetyPTWW(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "page_num", defaultValue = "1") Integer page,
            @RequestParam(value = "page_size", defaultValue = "500") Integer pageSize,
            @RequestParam(value = "project_id") Integer projectId,
            @RequestParam(value = "lastSync", required = false) String lastSync,
            @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id) {
        return tejrajService.findSafetyPTWW(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
    }

    @GetMapping(value = "/safety/ptw/find/{ptwId}")
    public ResponseEntity<Object> findSafetyPTWById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "ptwId") Integer ptwId) {
        return tejrajService.findSafetyPTWById(userId, token, ptwId);
    }

    @GetMapping(value = "/safety/ptw/find/V2/{ptwId}")
    public ResponseEntity<Object> findPTWById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "ptwId") Integer ptwId) {
        return tejrajService.findPTWById(userId, token, ptwId);
    }

    @GetMapping(value = "/safety/findPtwByFilter")
    public ResponseEntity<Object> findPTWByFilter(
            @RequestParam(value = "fromDate") String fromDate,
            @RequestParam(value = "toDate") String toDate,
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token) {
        return tejrajService.findPTWByFilter(fromDate, toDate, userId, token);
    }

    @PostMapping(value = "/safety/ptw/history/find")
    public ResponseEntity<Object> findPTWHistory(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ptwHistoryFindRequest) {
        return tejrajService.findPTWHistory(userId, token, ptwHistoryFindRequest);
    }

    @PostMapping(value = "/safety/findPtwCount")
    public ResponseEntity<Object> findPtwCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return tejrajService.findPtwCount(userId, token, dashboardCountRequest);
    }

    @PostMapping(value = "/safety/findObsCount")
    public ResponseEntity<Object> findObsCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return tejrajService.findObsCount(userId, token, dashboardCountRequest);
    }

    @PostMapping(value = "/safety/findEcCount")
    public ResponseEntity<Object> findEcCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return tejrajService.findEcCount(userId, token, dashboardCountRequest);
    }

    @PostMapping(value = "/safety/findTbtCount")
    public ResponseEntity<Object> findTbtCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return tejrajService.findTbtCount(userId, token, dashboardCountRequest);
    }

    @PostMapping(value = "/safety/uploadMultipleFiles", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadMultipleFiles(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam("eventId") Integer eventId,
            @RequestParam("eventName") String eventName,
            @RequestPart("file") MultipartFile[] files) {
        return tejrajService.uploadMultipleFiles(userId, token, eventId, eventName, files);
    }

    @PostMapping(value = "/safety/uploadCheckListMedia", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadCheckListMedia(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam("checklistAnswerId") Integer checklistAnswerId,
            @RequestPart("file") MultipartFile[] files) {
        return tejrajService.uploadCheckListMedia(userId, token, checklistAnswerId, files);
    }

    @GetMapping(value = "/safety/findUsersByProjectIdAndRoleId")
    public ResponseEntity<Object> findUsersByProjectIdAndRoleId(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "project_id") Integer projectId,
            @RequestParam(value = "roleId") Integer roleId,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return tejrajService.findUsersByProjectIdAndRoleId(userId, token, projectId, roleId, lastSync);
    }

    @GetMapping(value = "/safety/sendOBSNotification")
    public ResponseEntity<Object> sendOBSNotification(
            @RequestHeader(value = "level1LocationId") Integer level1LocationId,
            @RequestHeader(value = "obsId") Integer obsId,
            @RequestHeader(value = "user_id") Integer userId) {
        return tejrajService.sendOBSNotification(level1LocationId, obsId, userId);
    }

    @RequestMapping(value = "/master/answerType/findAll", method = RequestMethod.GET)
    public ResponseEntity<Object> findAllAnswerType(@RequestHeader(value = "userId") Integer userId,
                                                                      @RequestHeader(value = "token") String token, @RequestParam(value = "lastSync", required = false) String lastSync) {
       return tejrajService.findAllAnswerType(userId, token, lastSync);
    }

    @RequestMapping(value = "/master/checklistQuestions/findAll", method = RequestMethod.GET)
    public ResponseEntity<Object> findAllChecklistQestions(@RequestHeader(value = "userId") Integer userId,
                                                                                    @RequestHeader(value = "token") String token, @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return tejrajService.findAllChecklistQestions(userId, token, lastSync);
    }

    // Equipment
        @PostMapping(value = "/safety/equipment")
        public ResponseEntity<Object> saveEquipment(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object equipmentRequest) {
            return tejrajService.saveEquipment(userId, token, equipmentRequest);
        }

        @PutMapping(value = "/safety/equipment")
        public ResponseEntity<Object> updateEquipment(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object equipmentRequest) {
            return tejrajService.updateEquipment(userId, token, equipmentRequest);
        }

        @GetMapping(value = "/safety/equipment/find")
        public ResponseEntity<Object> findSafetyEquipment(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestParam(value = "page_num", defaultValue = "1") Integer page,
                @RequestParam(value = "page_size", defaultValue = "500") Integer pageSize,
                @RequestParam(value = "project_id") Integer projectId,
                @RequestParam(value = "lastSync", required = false) String lastSync,
                @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id) {
            return tejrajService.findSafetyEquipment(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
        }

        @PostMapping(value = "/safety/equipment/history/find")
        public ResponseEntity<Object> findEQHistory(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object eqHistoryFindRequest) {
            return tejrajService.findEQHistory(userId, token, eqHistoryFindRequest);
        }

        @GetMapping(value = "/safety/equipment/find/{equipmentId}")
        public ResponseEntity<Object> findSafetyEquipmentById(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @PathVariable(value = "equipmentId") Integer equipmentId) {
            return tejrajService.findSafetyEquipmentById(userId, token, equipmentId);
        }

        @GetMapping(value = "/safety/findOBSByFilter")
        public ResponseEntity<Object> findOBSByFilter(
                @RequestParam(value = "fromDate") String fromDate,
                @RequestParam(value = "toDate") String toDate,
                @RequestParam(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token) {
            return tejrajService.findOBSByFilter(fromDate, toDate, userId, token);
        }

        @GetMapping(value = "/safety/statistics/{projectId}")
        public ResponseEntity<Object> findSafetyStatistics(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @PathVariable(value = "projectId") Integer projectId) {
            return tejrajService.findSafetyStatistics(userId, token, projectId);
        }

        @PostMapping(value = "/safety/bulletin")
        public ResponseEntity<Object> sendBulletin(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object bulletinRequest) {
            return tejrajService.sendBulletin(userId, token, bulletinRequest);
        }

        @GetMapping(value = "/safety/bulletin/{projectId}")
        public ResponseEntity<Object> findBulletinByProjectId(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @PathVariable(value = "projectId") Integer projectId) {
            return tejrajService.findBulletinByProjectId(userId, token, projectId);
        }

        @GetMapping(value = "/safety/getSafetyDigitalLibrary")
        public ResponseEntity<Object> getSafetyDigitalLibrary(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestParam(value = "project_id") int projectId) {
            return tejrajService.getSafetyDigitalLibrary(userId, token, projectId);
        }

        @GetMapping(value = "/safety/ptw/getPendingPtw")
        public ResponseEntity<Object> getPendingPtw(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestParam(value = "projectId") int projectId) {
            return tejrajService.getPendingPtw(userId, token, projectId);
        }

    @GetMapping(value = "/safety/findCompanyUsers")
    public ResponseEntity<Object> findCompanyUsers(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token) {
        return tejrajService.findCompanyUsers(userId, token);
    }

    @GetMapping(value = "/safety/obsReport")
    public ResponseEntity<Object> getDataForObsReport(
            @RequestParam(value = "user_id") int userId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "obsId") int obsId,
            @RequestParam(value = "webCall", defaultValue = "false") boolean webCall) {

        return tejrajService.getDataForObsReport(userId, token, obsId, webCall);
    }

    // Emergency helpline

    @PostMapping(value = "/addEmergencyHelpline")
    public ResponseEntity<Object> addEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
                                                       @RequestHeader(value = "token") String token,
                                                       @RequestBody Object emergencyHelplineRequest)
    {
        return tejrajService.addEmergencyHelpLine(userId,token,emergencyHelplineRequest);
    }

    @GetMapping(value = "/getEmergencyHelpline")
    public ResponseEntity<List<Object>> getAllEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
                                                                @RequestHeader(value = "token") String token,
                                                                int projectId)
    {
        return tejrajService.getAllEmergencyHelpLine(userId,token,projectId);
    }

    @PutMapping(value = "/updateEmergencyHelpline")
    public ResponseEntity<Object> updateEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
                                                          @RequestHeader(value = "token") String token,
                                                          @RequestBody Object emergencyHelplineRequest)
    {
        return tejrajService.updateEmergencyHelpLine(userId,token,emergencyHelplineRequest);
    }

    @PutMapping(value = "/deleteEmergencyHelpline")
    public ResponseEntity<Object> deleteEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
                                                          @RequestHeader(value = "token") String token,
                                                          @RequestParam(value = "id") int id)

    {
        return tejrajService.deleteEmergencyHelpLine(userId,token,id);
    }

    // Hazards

    @PostMapping(value = "/hazards/upload/{projectId}", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadHazards(@RequestHeader(value = "userId") Integer userId,
                                                               @RequestHeader(value = "token") String token,
                                                               @PathVariable(value = "projectId") Integer projectId,
                                                               @RequestParam(value = "file") MultipartFile file)
    {
        return tejrajService.uploadHazards(userId,token,projectId,file);
    }

    @GetMapping(value = "/getHazardsByProjectId/{project_id}")
    public ResponseEntity<List<Object>> getHazardsByProjectId(@RequestHeader(value = "user_id") Integer userId,
                                                              @RequestHeader(value = "token") String token,
                                                              @PathVariable(value = "project_id") int projectId)
    {
        return tejrajService.getHazardsByProjectId(userId,token,projectId);
    }

    // Location master

    @RequestMapping(value = "/location/db/find", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findLocation(@RequestBody String data,
                                                            @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return tejrajService.findLocation(data,lastSync);
    }

    @RequestMapping(value = "/rest/v1/location/db/findall", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllLocationsFromDB(
            @RequestParam(value = "project_id", required = true) int pid,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam("user_id") int user_id,
            @RequestParam("token") String token)
    {
        return tejrajService.getAllLocationsFromDB(pid,page,pageSize,user_id,token);
    }

    @GetMapping(value = "/getLocationLevel1ByProjectId")
    public ResponseEntity<List<Object>> getLocationLevel1ByProjectId(@RequestParam(value = "projectId") int projectId)
    {
        return tejrajService.getLocationLevel1ByProjectId(projectId);
    }

    @RequestMapping(value = "/addLocations", consumes = "multipart/form-data", method = RequestMethod.POST)
    public String addLocations(@RequestParam(value = "userId") int userId,
                               @RequestParam(value = "file") MultipartFile file,
                               @RequestParam(value = "projectId") int projectId)
    {
        return tejrajService.addLocations(userId,file,projectId);
    }

    @RequestMapping(value = "/checklistDataUpload", consumes = "multipart/form-data", method = RequestMethod.POST)
    public Map<String, Integer> checklistDataUpload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "userId") int userId)
    {
        return tejrajService.checklistDataUpload(file,userId);
    }

    @RequestMapping(value = "/rest/v1/observation/master/db/findall", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllObservationsFromDB(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
                                                           @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                           @RequestHeader(value = "userId") Integer userId,
                                                           @RequestHeader(value = "token") String token,
                                                           @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return tejrajService.getAllObservationsFromDB(page,pageSize,userId,token,lastSync);
    }

    @RequestMapping(value = "/getRelatedLocation", method = RequestMethod.POST)
    public ResponseEntity<Object> getRelatedLocation(@RequestHeader("user_id") int user_id,
                                                @RequestHeader("token") String token,
                                                @RequestBody(required = false) Object relatedLocationRequest)
    {
        return tejrajService.getRelatedLocation(user_id, token, relatedLocationRequest);
    }

    // Progress Report

    @PostMapping(value = "/progressReport")
    public ResponseEntity<Object> saveProgressReport(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object progressReportRequest)
    {
        return tejrajService.saveProgressReport(userId,token,progressReportRequest);
    }

    @GetMapping(value = "/progressReport")
    public ResponseEntity<List<Object>> getProgressReport(@RequestHeader(value = "userId") Integer userId,
                                                          @RequestHeader(value = "token") String token)

    {
        return tejrajService.getProgressReport(userId,token);
    }

    @GetMapping(value = "/progressReport/{id}")
    public ResponseEntity<Object> getProgressReportById(@RequestHeader(value = "userId") Integer userId,
                                                        @RequestHeader(value = "token") String token,
                                                        @PathVariable(value = "id") long progressReportId)
    {
        return tejrajService.getProgressReportById(userId,token,progressReportId);
    }

    // Projects

    @RequestMapping(value = "/rest/v1/projects", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> restProjects(@RequestParam("user_id") int user_id,
                                                            @RequestParam("token") String token,
                                                            @RequestParam(value = "lastSync", required = false) String lastSync) throws JsonParseException, JsonMappingException, IOException

    {
        return tejrajService.restProjects(user_id,token,lastSync);
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public Object addProject(@RequestHeader("userId") int userId,
                             @RequestHeader("token") String token,
                             @RequestBody Object projectCreateRequest)
    {
        return tejrajService.addProject(userId,token,projectCreateRequest);
    }

    @RequestMapping(value = "/project", method = RequestMethod.PUT)
    public Object updateProject(@RequestHeader("userId") int userId,
                                @RequestHeader("token") String token,
                                @RequestBody Object projectUpdateRequest)
    {
        return tejrajService.updateProject(userId,token,projectUpdateRequest);
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public List<Object> getProjects(@RequestHeader("userId") int userId,
                                    @RequestHeader("token") String token)
    {
        return tejrajService.getProjects(userId,token);
    }

    @RequestMapping(value = "/downloadProjects", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadUsersExcel(
            @RequestParam(value = "userId")   Integer userId,
            @RequestParam(value = "token")    String  token,
            @RequestParam(value = "pageNum",    defaultValue = "1")    int pageNum,
            @RequestParam(value = "pageSize",   defaultValue = "1000") int pageSize)
    {
        return tejrajService.downloadProjects(userId, token, pageNum, pageSize);
    }

    // QC controller
    @RequestMapping(value = "/crfi", method = RequestMethod.POST)
    public ResponseEntity<Object> createCRFI(@RequestHeader(value = "userId") int userId,
                                             @RequestHeader(value = "token") String token,
                                             @RequestBody Object activityInspectionRequest)
    {
        return tejrajService.createCRFI(userId,token,activityInspectionRequest);
    }

    @RequestMapping(value = "/crfi", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCRFI(@RequestHeader(value = "userId") int userId,
                                             @RequestHeader(value = "token") String token,
                                             @RequestBody Object activityInspectionUpdateRequest)
    {
        return tejrajService.updateCRFI(userId,token,activityInspectionUpdateRequest);
    }

    @RequestMapping(value = "/crfi", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getCRFI(@RequestHeader(value = "userId") int userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestParam(value = "crfiId") long crfiId,
                                                @RequestParam(value = "projectId") long projectId)
    {
        return tejrajService.getCRFI(userId,token,crfiId,projectId);
    }

    @RequestMapping(value = "/crfiDetails", method = RequestMethod.GET)
    public ResponseEntity<Object> getCRFIDetails(@RequestHeader(value = "userId") int userId,
                                                 @RequestHeader(value = "token") String token,
                                                 @RequestParam(value = "crfiId") long crfiId)
    {
        return tejrajService.getCRFIDetails(userId,token,crfiId);
    }

    @RequestMapping(value = "/crfiReport", method = RequestMethod.GET)
    public ResponseEntity<Object> getDataForRFIReport(@RequestParam(value = "user_id") int userId,
                                                 @RequestParam(value = "token") String token,
                                                 @RequestParam(value = "crfiId",required = true) int crfiId,
                                                 @RequestParam(value = "webCall", defaultValue = "false", required = false) boolean webCall)
    {
        return tejrajService.getDataForRFIReport(userId,token,crfiId,webCall);
    }

    @RequestMapping(value = "/filter/crfi", method = RequestMethod.POST)
    public ResponseEntity<Object> getObsFilter(@RequestHeader(value = "userId") int userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestBody Object filterCrfiRequest)
    {
        return tejrajService.getObsFilter(userId,token,filterCrfiRequest);
    }

    // QC OBS API

    @RequestMapping(value = "/qc/obs", method = RequestMethod.POST)
    public ResponseEntity<Object> createQCObservation(@RequestHeader(value = "userId") Integer userId,
                                                      @RequestHeader(value = "token") String token,
                                                      @RequestBody Object obsRequest)
    {
        return tejrajService.createQCObservation(userId,token,obsRequest);
    }

    @RequestMapping(value = "/qc/obs", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getObs(@RequestHeader(value = "userId") int userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestParam(value = "obsId") long obsId,
                                               @RequestParam(value = "projectId") long projectId)
    {
        return tejrajService.getObs(userId,token,obsId,projectId);
    }


    @RequestMapping(value = "/qc/obs", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateOBS(@RequestHeader(value = "userId") int userId,
                                            @RequestHeader(value = "token") String token,
                                            @RequestBody Object obsUpdateRequest)
    {
        return tejrajService.updateOBS(userId,token,obsUpdateRequest);
    }

    @RequestMapping(value = "/qc/obsDetails", method = RequestMethod.GET)
    public ResponseEntity<Object> getOBSDetails(@RequestHeader(value = "userId") int userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestParam(value = "obsId") long obsId)
    {
        return tejrajService.getOBSDetails(userId,token,obsId);
    }

    @RequestMapping(value = "/qc/obsReport", method = RequestMethod.GET)
    public ResponseEntity<Object> getDataForQCObsReport(@RequestParam(value = "user_id") int userId,
                                                 @RequestParam(value = "token") String token,
                                                 @RequestParam(value = "obsId",required = true) int obsId,
                                                 @RequestParam(value = "webCall", defaultValue = "false", required = false) boolean webCall)
    {
        return tejrajService.getDataForQCObsReport(userId, token, obsId, webCall);
    }

    @RequestMapping(value = "/qcStages", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAll()
    {
        return tejrajService.getAll();
    }

    @RequestMapping(value = "/qcStages", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(@RequestParam String stageName)
    {
        return tejrajService.create(stageName);
    }

    // Report controller

    @RequestMapping(value = "/rorReport", method = RequestMethod.GET)
    public void rorReport()
    {
        tejrajService.rorReport();
    }

    @RequestMapping(value = "/ptwReport", method = RequestMethod.POST)
    public void ptwReport(@RequestBody Object ptwReportRequest)
    {
        tejrajService.ptwReport(ptwReportRequest);
    }

    @RequestMapping(value = "/ptwReportDownload", method = RequestMethod.GET)
    public ResponseEntity<Object> ptwReportDownload(
            @RequestParam(value = "user_id") int userId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "ptwId",required = true) int ptwId,
            @RequestParam(value = "webCall", defaultValue = "true", required = false) boolean webCall)
    {
       return tejrajService.ptwReportDownload(userId,token,ptwId,webCall);
    }

    @RequestMapping(value = "/equipmentReportDownload", method = RequestMethod.GET)
    public ResponseEntity<Object> equipmentReportDownload(
            @RequestParam(value = "user_id") int userId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "equipmentId") int equipmentId,
            @RequestParam(value = "webCall", defaultValue = "true", required = false) boolean webCall)
    {
       return tejrajService.equipmentReportDownload(userId,token,equipmentId,webCall);
    }

    @RequestMapping(value = "/safetyObsReport", method = RequestMethod.GET)
    public void safetyObsReport()
    {
        tejrajService.safetyObsReport();
    }

    @RequestMapping(value = "/escalateOBSReport", method = RequestMethod.GET)
    public void escalateOBSReport()
    {
        tejrajService.escalateOBSReport();
    }

    @RequestMapping(value = "/safetyObsReportByFilter", method = RequestMethod.GET)
    public ResponseEntity<Object> safetyObsReportByFilter(@RequestParam(value = "fromDate") String fromDate,
                                                          @RequestParam(value = "toDate") String toDate,
                                                          @RequestParam(value = "userId") Integer userId,
                                                          @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId)
    {
        return tejrajService.safetyObsReportByFilter(fromDate,toDate,userId,projectId);
    }

    @GetMapping("/downloadMemoPdf")
    public ResponseEntity<Object> downloadMemoPdf(@RequestHeader("user_id") int user_id,
                                                  @RequestHeader("token") String token,
                                                  @RequestParam(name = "obsId", required = false, defaultValue = "0") int obsId)
    {
        return tejrajService.downloadMemoPdf(user_id,token,obsId);
    }


    @GetMapping("/downloadDebitPdf")
    public ResponseEntity<Object> downloadDebitPdf(@RequestHeader("user_id") int user_id,
                                                   @RequestHeader("token") String token,
                                                   @RequestParam(name = "debitId", required = false, defaultValue = "0") int debitId)
    {
        return tejrajService.downloadDebitPdf(user_id,token,debitId);
    }


    @GetMapping("/downloadIncidentPdf")
    public ResponseEntity<Object> downloadIncidentPdf(@RequestHeader("user_id") int user_id,
                                                      @RequestHeader("token") String token,
                                                      @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId)
    {
      return tejrajService.downloadIncidentPdf(user_id,token,incidentId);
    }

    @RequestMapping(value = "/downloadSnagingPdf", method = RequestMethod.POST)
    public ResponseEntity<Object> downloadSnagingPdf(@RequestHeader("user_id") int user_id,
                                                @RequestHeader("token") String token,
                                                @RequestBody Object filterRequest)
    {
        return tejrajService.downloadSnagingPdf(user_id,token,filterRequest);
    }
    @GetMapping("/sendIncidentPdf")
    public ResponseEntity<Object> sendIncidentPdf(@RequestHeader("user_id") int user_id,
                                                  @RequestHeader("token") String token,
                                                  @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId)
    {
      return tejrajService.sendIncidentPdf(user_id,token,incidentId);
    }


    @GetMapping("/downloadTBTPdf")
    public ResponseEntity<Object> downloadTBTPdf(@RequestHeader("user_id") int user_id,
                                                 @RequestHeader("token") String token,
                                                 @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId)
    {
       return tejrajService.downloadTBTPdf(user_id,token,tbtId);
    }

    @RequestMapping(value = "/equipmentReport", method = RequestMethod.POST)
    public ResponseEntity<Object> equipmentReport(@RequestBody Object equipmentReportRequest)
    {
        return tejrajService.equipmentReport(equipmentReportRequest);
    }

    @RequestMapping(value = "/obsreport", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getObsReport(@RequestParam(value = "user_id") int userId,
                                                            @RequestParam(value = "token") String token,
                                                            @RequestParam(value = "obsId",required = false) int obsId)
    {
        return tejrajService.getObsReport(userId,token,obsId);
    }


    @RequestMapping(value = "/weeklyStatusReport", method = RequestMethod.GET)
    public void weeklyStatusReport(@RequestParam(value = "projectId") int projectId,
                                   @RequestParam(value = "fromDate") String fromDate,
                                   @RequestParam(value = "toDate") String toDate,
                                   @RequestParam(value = "locationBased") int locationBased)
    {
        tejrajService.weeklyStatusReport(projectId,fromDate,toDate,locationBased);
    }

    @GetMapping("/downloadWorkerPdf")
    public ResponseEntity<Object> downloadWorkerPdf(@RequestParam("user_id") int user_id,
                                                    @RequestParam("token") String token,
                                                    @RequestParam(name = "workerId", required = true, defaultValue = "0") int workerId,
                                                    @RequestParam(value = "webCall", defaultValue = "true", required = false) boolean webCall)
    {
       return tejrajService.downloadWorkerPdf(user_id,token,workerId,webCall);
    }


    // Incident controller

    @RequestMapping(value = "/incident", method = RequestMethod.POST)
    public ResponseEntity<Object> saveIncident(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestBody Object request)
    {
        return tejrajService.saveIncident(userId,token,request);
    }


    @RequestMapping(value = "/incident", method = RequestMethod.GET)
    public ResponseEntity<Object> findSafetyIncident(@RequestHeader(value = "userId") Integer userId,
                                                     @RequestHeader(value = "token") String token,
                                                     @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                                     @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                                     @RequestParam(value = "project_id") Integer projectId)
    {
        return tejrajService.findSafetyIncident(userId,token,page,pageSize,projectId);
    }

    @RequestMapping(value = "/incident/{incidentId}", method = RequestMethod.GET)
    public ResponseEntity<Object> findIncidentById(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @PathVariable(value = "incidentId") Integer incidentId)
    {
        return tejrajService.findIncidentById(userId,token,incidentId);
    }

    // Safety worker controller

    @PostMapping(value = "/SafetyWorkers")
    public ResponseEntity<Object> saveSafetyWorkers(@RequestHeader(value = "user_id") int userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestBody Object safetyWorkersRequest)
    {
        return tejrajService.saveSafetyWorkers(userId,token,safetyWorkersRequest);
    }

    @GetMapping(value = "/SafetyWorkers")
    public ResponseEntity<List<Object>> getSafetyWorkers(@RequestHeader(value = "user_id") int userId,
                                                         @RequestHeader(value = "token") String token,
                                                         @RequestParam(value = "project_id") long projectId)
    {
        return tejrajService.getSafetyWorkers(userId,token,projectId);
    }

    @GetMapping(value = "/SafetyWorkers/{id}")
    public ResponseEntity<Object> getSafetyWorkersById(@RequestHeader(value = "user_id") int userId,
                                                       @RequestHeader(value = "token") String token,
                                                       @RequestParam(value = "id") long workerId)
    {
        return tejrajService.getSafetyWorkersById(userId,token,workerId);
    }

    @PutMapping(value = "/updateSafetyWorker/{id}")
    public ResponseEntity<Object> updateSafetyWorkerById(@RequestHeader(value = "user_id") int userId,
                                                         @RequestHeader(value = "token") String token,
                                                         @RequestParam(value = "id") long workerId,
                                                         @RequestBody Object request)
    {
        return tejrajService.updateSafetyWorkerById(userId,token,workerId,request);
    }

    // Unit Master

    @GetMapping(value = "/unitMaster")
    public ResponseEntity<List<Object>> getAllUnitMaster(@RequestHeader(value = "userId") Integer userId,
                                                         @RequestHeader(value = "token") String token)
    {
        return tejrajService.getAllUnitMaster(userId,token);
    }

    @GetMapping(value = "/unitMaster/{id}")
    public ResponseEntity<Object> getUnitMasterById(@RequestHeader(value = "userId") Integer userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @PathVariable(value = "id") long unitMasterId)
    {
        return tejrajService.getUnitMasterById(userId,token,unitMasterId);
    }

    //Snag Controller
    @RequestMapping(value = "/snag/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createSnag(@RequestHeader(value = "userId") Integer userId,
                                                         @RequestHeader(value = "token") String token, @RequestBody Object snagRequest)
    {
        return tejrajService.createSnag(userId, token, snagRequest);
    }

    @RequestMapping(value = "/snag/find", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getSnag(@RequestHeader(value = "userId") int userId,
                                                  @RequestHeader(value = "token") String token,
                                                  @RequestParam(value = "snagId") long snagId,
                                                  @RequestParam(value = "projectId") long projectId)
    {
        return tejrajService.getSnag(userId, token, snagId, projectId);
    }

    @RequestMapping(value = "/snag/filter", method = RequestMethod.POST)
    public ResponseEntity<Object> getSnagFilter(@RequestHeader(value = "userId") int userId,
                                                      @RequestHeader(value = "token") String token,
                                                      @RequestBody Object filterObsRequest)
    {
        return tejrajService.getSnagFilter(userId, token, filterObsRequest);
    }

    @RequestMapping(value = "/snag/update", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateSnag(@RequestHeader(value = "userId") int userId,
                                                         @RequestHeader(value = "token") String token,
                                                         @RequestBody Object obsUpdateRequest){
        return tejrajService.updateSnag(userId, token, obsUpdateRequest);
    }

    @RequestMapping(value = "/snag/details", method = RequestMethod.GET)
    public ResponseEntity<Object> getSnagDetails(@RequestHeader(value = "userId") int userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @RequestParam(value = "snagId") long obsId){
        return tejrajService.getSnagDetails(userId, token, obsId);
    }

    @RequestMapping(value = "/snag/report", method = RequestMethod.GET)
    public ResponseEntity<Object> getDataForSnagReport(@RequestParam(value = "user_id") int userId,
                                                  @RequestParam(value = "token") String token,
                                                  @RequestParam(value = "snagId",required = true) int snagId,
                                                  @RequestParam(value = "webCall", defaultValue = "false", required = false) boolean webCall)
    {
        return tejrajService.getDataForSnagReport(userId, token, snagId, webCall);
    }

    // User controller
    @RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Object> addUser(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestBody Object userRequestModel)
    {
        return tejrajService.addUser(userId,token,userRequestModel);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Object> UpdateUser(@RequestHeader(value = "userId") Integer userId,
                                             @RequestHeader(value = "token") String token,
                                             @RequestBody Object userRequestModel)
    {
        return tejrajService.UpdateUser(userId,token,userRequestModel);
    }

    @RequestMapping(value = "/user/fetch", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<List<Object>> getUsers(@RequestHeader(value = "userId") Integer userId,
                                                 @RequestHeader(value = "token") String token,
                                                 @RequestParam(value = "projectId", required = false, defaultValue = "0") int projectId,
                                                 @RequestParam(value = "companyId", required = false, defaultValue = "0") int companyId,
                                                 @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "1000") int pageSize)
    {
        return tejrajService.getUsers(userId,token,projectId,companyId,pageNum,pageSize);
    }

    @PostMapping(value = "/user/filter")
    public ResponseEntity<Object> filterUsers(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token")  String  token,
            @RequestBody Object filterRequest){
        return tejrajService.filterUsers(userId, token, filterRequest);
    }

    @GetMapping(value = "/user/getNonProjectUsers")
    public ResponseEntity<List<Object>> getNonProjectUsers(@RequestParam(value = "projectId") int projectId,
                                                           @RequestParam(value = "companyId") int companyId)
    {
        return tejrajService.getNonProjectUsers(projectId,companyId);
    }

    @RequestMapping(value = "/user/userMapping", method = RequestMethod.POST)
    public ResponseEntity<Object> userMapping(@RequestBody Object userMappingRequest)
    {
        return tejrajService.userMapping(userMappingRequest);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = {"application/json"})
    public ResponseEntity<Object> deleteUser(@RequestParam(value = "user_id") Integer userId)
    {
        return tejrajService.deleteUser(userId);
    }

    @RequestMapping(value = "/user/updateUserStatus", method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<Object> updateUserStatus(@RequestParam(value = "user_id") Integer userId,
                                                   @RequestParam(value = "active") int active)
    {
        return tejrajService.updateUserStatus(userId, active);
    }

    @RequestMapping(value = "/user/downloadUsers", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadUsersExcel(
            @RequestParam(value = "userId")   Integer userId,
            @RequestParam(value = "token")    String  token,
            @RequestParam(value = "projectId",  required = false, defaultValue = "0")    int projectId,
            @RequestParam(value = "companyId",  required = false, defaultValue = "0")    int companyId,
            @RequestParam(value = "pageNum",    defaultValue = "1")    int pageNum,
            @RequestParam(value = "pageSize",   defaultValue = "1000") int pageSize){
            return tejrajService.downloadUsersExcel(userId, token, projectId, companyId, pageNum, pageSize);
    }
}
