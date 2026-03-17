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

    // Safety TBT API's

    @PostMapping(value = "/SafetyTBT")
    ResponseEntity<Object> saveSafetyTBT(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object safetyTBTRequest)
    {
        return qhse_8081_Service.saveSafetyTBT(userId,token,safetyTBTRequest);
    }

    @GetMapping(value = "/SafetyTBT")
    ResponseEntity<List<Object>> getSafetyTBT(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "projectId", required = false, defaultValue = "0") int projectId)
    {
        return qhse_8081_Service.getSafetyTBT(userId,token,projectId);
    }

    @GetMapping(value = "/getSafetyTbtById/{id}")
    ResponseEntity<Object> getSafetyTbtById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "id") Integer tbtId)
    {
        return qhse_8081_Service.getSafetyTbtById(userId,token,tbtId);
    }

    // safety controller API's

    // safety observation API's
    @PostMapping(value = "/safety/obs/create")
    public ResponseEntity<Object> createSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object obsRequest) {
        return qhse_8081_Service.createSafetyObservation(userId, token, obsRequest);
    }

    @PostMapping(value = "/safety/obs/update")
    public ResponseEntity<Object> updateSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object updateRequest) {
        return qhse_8081_Service.updateSafetyObservation(userId, token, updateRequest);
    }

    @PostMapping(value = "/safety/obs/find")
    public ResponseEntity<Object> findSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync,
            @RequestBody Object findRequest) {
        return qhse_8081_Service.findSafetyObservation(userId, token, lastSync, findRequest);
    }

    @GetMapping(value = "/safety/obs/find/{obsId}")
    public ResponseEntity<Object> findSafetyObservationByObsId(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "obsId") Integer obsId) {
        return qhse_8081_Service.findSafetyObservationByObsId(userId, token, obsId);
    }

    @PostMapping(value = "/safety/obs/history/find")
    public ResponseEntity<Object> findObservationRequestHistory(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object historyRequest) {
        return qhse_8081_Service.findObservationRequestHistory(userId, token, historyRequest);
    }

    // MASTER API

    @GetMapping(value = "/safety/master/unsafeAct/findAll")
    public ResponseEntity<Object> findAllUnsafeAct(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return qhse_8081_Service.findAllUnsafeAct(userId, token, lastSync);
    }

    @GetMapping(value = "/safety/master/unsafeCondition/findAll")
    public ResponseEntity<Object> findAllUnsafeCondition(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return qhse_8081_Service.findAllUnsafeCondition(userId, token, lastSync);
    }

    @GetMapping(value = "/safety/master/typeOfWork/findAll")
    public ResponseEntity<Object> findAllTypeOfWork(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return qhse_8081_Service.findAllTypeOfWork(userId, token, lastSync);
    }

    @GetMapping(value = "/safety/master/checklistQuestions/findAll")
    public ResponseEntity<Object> findAllChecklistQuestions(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return qhse_8081_Service.findAllChecklistQuestions(userId, token, lastSync);
    }

    @GetMapping(value = "/safety/master/typeOfWorkChecklistMapping")
    public ResponseEntity<Object> findAllTypeOfWorkChecklistMapping(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync)
    {
        return qhse_8081_Service.findAllTypeOfWorkChecklistMapping(userId, token, lastSync);
    }

    // FOR PTW API

    @PostMapping(value = "/safety/ptw")
    public ResponseEntity<Object> createSafetyPTW(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ptwRequest) {
        return qhse_8081_Service.createSafetyPTW(userId, token, ptwRequest);
    }

    @PutMapping(value = "/safety/ptw")
    public ResponseEntity<Object> updateSafetyPTW(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ptwRequest) {
        return qhse_8081_Service.updateSafetyPTW(userId, token, ptwRequest);
    }

    @PutMapping(value = "/safety/ptwStatus")
    public ResponseEntity<Object> updatePTWStatus(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object request) {
        return qhse_8081_Service.updatePTWStatus(userId, token, request);
    }

    @PutMapping(value = "/safety/ptwClose")
    public ResponseEntity<Object> updateUnclosedPTW() {
        return qhse_8081_Service.updateUnclosedPTW();
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
        return qhse_8081_Service.findSafetyPTW(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
    }

    @GetMapping(value = "/safety/ptw/findsp")
    public ResponseEntity<Object> findSafetyPTWSP(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "page_num", defaultValue = "1") Integer page,
            @RequestParam(value = "page_size", defaultValue = "500") Integer pageSize,
            @RequestParam(value = "project_id") Integer projectId) {
        return qhse_8081_Service.findSafetyPTWSP(userId, token, page, pageSize, projectId);
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
        return qhse_8081_Service.findSafetyPTWW(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
    }

    @GetMapping(value = "/safety/ptw/find/{ptwId}")
    public ResponseEntity<Object> findSafetyPTWById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "ptwId") Integer ptwId) {
        return qhse_8081_Service.findSafetyPTWById(userId, token, ptwId);
    }

    @GetMapping(value = "/safety/ptw/find/V2/{ptwId}")
    public ResponseEntity<Object> findPTWById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "ptwId") Integer ptwId) {
        return qhse_8081_Service.findPTWById(userId, token, ptwId);
    }

    @GetMapping(value = "/safety/findPtwByFilter")
    public ResponseEntity<Object> findPTWByFilter(
            @RequestParam(value = "fromDate") String fromDate,
            @RequestParam(value = "toDate") String toDate,
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token) {
        return qhse_8081_Service.findPTWByFilter(fromDate, toDate, userId, token);
    }

    @PostMapping(value = "/safety/ptw/history/find")
    public ResponseEntity<Object> findPTWHistory(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ptwHistoryFindRequest) {
        return qhse_8081_Service.findPTWHistory(userId, token, ptwHistoryFindRequest);
    }

    @PostMapping(value = "/safety/findPtwCount")
    public ResponseEntity<Object> findPtwCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return qhse_8081_Service.findPtwCount(userId, token, dashboardCountRequest);
    }

    @PostMapping(value = "/safety/findObsCount")
    public ResponseEntity<Object> findObsCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return qhse_8081_Service.findObsCount(userId, token, dashboardCountRequest);
    }

    @PostMapping(value = "/safety/findEcCount")
    public ResponseEntity<Object> findEcCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return qhse_8081_Service.findEcCount(userId, token, dashboardCountRequest);
    }

    @PostMapping(value = "/safety/findTbtCount")
    public ResponseEntity<Object> findTbtCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return qhse_8081_Service.findTbtCount(userId, token, dashboardCountRequest);
    }

    @PostMapping(value = "/safety/uploadMultipleFiles", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadMultipleFiles(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam("eventId") Integer eventId,
            @RequestParam("eventName") String eventName,
            @RequestPart("file") MultipartFile[] files) {
        return qhse_8081_Service.uploadMultipleFiles(userId, token, eventId, eventName, files);
    }

    @PostMapping(value = "/safety/uploadCheckListMedia", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadCheckListMedia(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam("checklistAnswerId") Integer checklistAnswerId,
            @RequestPart("file") MultipartFile[] files) {
        return qhse_8081_Service.uploadCheckListMedia(userId, token, checklistAnswerId, files);
    }

    @GetMapping(value = "/safety/findUsersByProjectIdAndRoleId")
    public ResponseEntity<Object> findUsersByProjectIdAndRoleId(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "project_id") Integer projectId,
            @RequestParam(value = "roleId") Integer roleId,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return qhse_8081_Service.findUsersByProjectIdAndRoleId(userId, token, projectId, roleId, lastSync);
    }

    @GetMapping(value = "/safety/sendOBSNotification")
    public ResponseEntity<Object> sendOBSNotification(
            @RequestHeader(value = "level1LocationId") Integer level1LocationId,
            @RequestHeader(value = "obsId") Integer obsId,
            @RequestHeader(value = "user_id") Integer userId) {
        return qhse_8081_Service.sendOBSNotification(level1LocationId, obsId, userId);
    }

    // Equipment

        @PostMapping(value = "/safety/equipment")
        public ResponseEntity<Object> saveEquipment(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object equipmentRequest) {
            return qhse_8081_Service.saveEquipment(userId, token, equipmentRequest);
        }

        @PutMapping(value = "/safety/equipment")
        public ResponseEntity<Object> updateEquipment(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object equipmentRequest) {
            return qhse_8081_Service.updateEquipment(userId, token, equipmentRequest);
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
            return qhse_8081_Service.findSafetyEquipment(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
        }

        @PostMapping(value = "/safety/equipment/history/find")
        public ResponseEntity<Object> findEQHistory(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object eqHistoryFindRequest) {
            return qhse_8081_Service.findEQHistory(userId, token, eqHistoryFindRequest);
        }

        @GetMapping(value = "/safety/equipment/find/{equipmentId}")
        public ResponseEntity<Object> findSafetyEquipmentById(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @PathVariable(value = "equipmentId") Integer equipmentId) {
            return qhse_8081_Service.findSafetyEquipmentById(userId, token, equipmentId);
        }

        @GetMapping(value = "/safety/findOBSByFilter")
        public ResponseEntity<Object> findOBSByFilter(
                @RequestParam(value = "fromDate") String fromDate,
                @RequestParam(value = "toDate") String toDate,
                @RequestParam(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token) {
            return qhse_8081_Service.findOBSByFilter(fromDate, toDate, userId, token);
        }

        @GetMapping(value = "/safety/statistics/{projectId}")
        public ResponseEntity<Object> findSafetyStatistics(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @PathVariable(value = "projectId") Integer projectId) {
            return qhse_8081_Service.findSafetyStatistics(userId, token, projectId);
        }

        @PostMapping(value = "/safety/bulletin")
        public ResponseEntity<Object> sendBulletin(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object bulletinRequest) {
            return qhse_8081_Service.sendBulletin(userId, token, bulletinRequest);
        }

        @GetMapping(value = "/safety/bulletin/{projectId}")
        public ResponseEntity<Object> findBulletinByProjectId(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @PathVariable(value = "projectId") Integer projectId) {
            return qhse_8081_Service.findBulletinByProjectId(userId, token, projectId);
        }

        @GetMapping(value = "/safety/getSafetyDigitalLibrary")
        public ResponseEntity<Object> getSafetyDigitalLibrary(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestParam(value = "project_id") int projectId) {
            return qhse_8081_Service.getSafetyDigitalLibrary(userId, token, projectId);
        }

        @GetMapping(value = "/safety/ptw/getPendingPtw")
        public ResponseEntity<Object> getPendingPtw(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestParam(value = "projectId") int projectId) {
            return qhse_8081_Service.getPendingPtw(userId, token, projectId);
        }

    @GetMapping(value = "/safety/findCompanyUsers")
    public ResponseEntity<Object> findCompanyUsers(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token) {
        return qhse_8081_Service.findCompanyUsers(userId, token);
    }

    @GetMapping(value = "/safety/obsReport")
    public ResponseEntity<Object> getDataForObsReport(
            @RequestParam(value = "user_id") int userId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "obsId") int obsId,
            @RequestParam(value = "webCall", defaultValue = "false") boolean webCall) {

        return qhse_8081_Service.getDataForObsReport(userId, token, obsId, webCall);
    }
}
