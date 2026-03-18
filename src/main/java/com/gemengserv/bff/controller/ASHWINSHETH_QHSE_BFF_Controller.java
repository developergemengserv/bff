package com.gemengserv.bff.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gemengserv.bff.service.ASHWINSHETH_QHSE_8082;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ashwinshethbff")
public class ASHWINSHETH_QHSE_BFF_Controller {

    @Autowired
    ASHWINSHETH_QHSE_8082 ashwinsheth_qhse_8082_service;
    
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
            return ashwinsheth_qhse_8082_service.getActivitiesByUser(userId, projectId, token, pageSize, page, lastSync);
        }

        @RequestMapping(value = "/rest/v1/activity/checklist/master", method = RequestMethod.GET)
        public ResponseEntity<LinkedHashMap<String, Object>> getChecklists(
        @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
        @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
        @RequestParam("user_id") int user_id, @RequestParam("project_id") int project_id,
        @RequestParam("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync)
        {
            return ashwinsheth_qhse_8082_service.getChecklists(page, pageSize, user_id, project_id, token, lastSync);
        }

        @GetMapping(value = "/getActivities")
        public ResponseEntity<List<Object>> getActivities()
        {
            return ashwinsheth_qhse_8082_service.getActivities();
        }

        @RequestMapping(value = "/addActivities", consumes = "multipart/form-data", method = RequestMethod.POST)
        public String addActivities(@RequestParam(value = "file") MultipartFile file,
        @RequestParam(value = "userId") int userId)
        {
            return ashwinsheth_qhse_8082_service.addActivities(file,userId);
        }

        @RequestMapping(value = "/addActivitiesChecklist", consumes = "multipart/form-data", method = RequestMethod.POST)
        public String addLocations(@RequestParam(value = "userId") int userId,
        @RequestParam(value = "file") MultipartFile file)
        {
            return ashwinsheth_qhse_8082_service.addLocations(userId,file);
        }

//    ActivityTypeOfWorkMappingController
        @GetMapping(value = "/rest/v1/activityTypeOfWork", produces = "application/json")
        public ResponseEntity<Object> getActivityTypeOfWorkMapping(@RequestHeader("user_id") int userId,
        @RequestHeader("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync)
        {
            return ashwinsheth_qhse_8082_service.getActivityTypeOfWorkMapping(userId, token, lastSync);
        }

//   ActivityUnitMappingController
        @GetMapping(value = "activityUnitMapping")
        public List<Object> getAllActivityUnitMapping()
        {
            return ashwinsheth_qhse_8082_service.getAllActivityUnitMapping();
        }

        //  CommonController
        @RequestMapping(value = "/rest/v1/configuration", method = RequestMethod.GET)
        public ResponseEntity<Object> getConfiguration(@RequestParam("package_id") String package_id) {
        return ashwinsheth_qhse_8082_service.getConfiguration(package_id);
    }

        @RequestMapping(value = "/rest/v1/checkotp", method = RequestMethod.GET)
        public ResponseEntity<Map<String, Object>> checkotp(@RequestParam("otp") int otp, @RequestParam("user_id") int user_id, @RequestParam("version") String version,
        @RequestParam("device_code") String device_code) {
        return ashwinsheth_qhse_8082_service.checkotp(otp, user_id, version, device_code);
    }

        @RequestMapping(value = "/rest/v1/logout", method = RequestMethod.GET)
        public ResponseEntity<Map<String, Object>> logout(@RequestParam("user_id") int user_id, @RequestParam("version") String version,
        @RequestParam("device_code") String device_code) {
        return ashwinsheth_qhse_8082_service.logout(user_id, version, device_code);
    }

        @RequestMapping(value = "/rest/v1/userDetails", method = RequestMethod.GET)
        public ResponseEntity<?> userDetails(@RequestParam("user_id") int user_id,
        @RequestParam("token") String token)
        {
            return ashwinsheth_qhse_8082_service.userDetails(user_id, token);
        }

        @RequestMapping(value = "/company", method = RequestMethod.POST)
        public Object addCompany(@RequestHeader("userId") int userId,
        @RequestHeader("token") String token,
        @RequestBody Object companyCreateRequest)
        {
            return ashwinsheth_qhse_8082_service.addCompany(userId,token,companyCreateRequest);
        }

        @RequestMapping(value = "/company", method = RequestMethod.PUT)
        public Object updateCompany(@RequestHeader("userId") int userId,
        @RequestHeader("token") String token,
        @RequestBody Object companyUpdateRequest)
        {
            return ashwinsheth_qhse_8082_service.updateCompany(userId,token,companyUpdateRequest);
        }

        @GetMapping("/company")
        public Object getCombinedResponse(@RequestParam("userId") int userId,
        @RequestParam("token") String token) {
        return ashwinsheth_qhse_8082_service.getCompanies(userId, token);
    }

        @RequestMapping(value = "/deleteCompany", method = RequestMethod.DELETE, produces = {"application/json"})
        public ResponseEntity<?> deleteCompany(@RequestParam(value = "companyId") Integer companyId)
        {
            return ashwinsheth_qhse_8082_service.deleteCompany(companyId);
        }

        @GetMapping("/registerUser")
        public Object registerUser(@RequestBody Object userRegisterRequest) {
        return ashwinsheth_qhse_8082_service.registerUser(userRegisterRequest);
    }

        @GetMapping("/rest/v2/login")
        public ResponseEntity<?> loginV2(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ashwinsheth_qhse_8082_service.loginAPI(username, password);
    }

        @RequestMapping(value = "/rest/v1/login", method = RequestMethod.GET)
        public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username, @RequestParam("password") String password)
        {
            return ashwinsheth_qhse_8082_service.login(username, password);
        }

        @RequestMapping(value = "/rest/v1/media/find", method = RequestMethod.GET)
        public void doDownload(@RequestParam("media_url") String mediaUrl, @RequestParam("user_id") int user_id, @RequestParam("token") String token, HttpServletResponse response)
            throws IOException
        {
            ashwinsheth_qhse_8082_service.doDownload(mediaUrl,user_id,token,response);
        }

        @PostMapping(value = "/rest/v1/upload/signature", consumes = "multipart/form-data")
        public ResponseEntity<Object> uploadSignature(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "file") MultipartFile file)
        {
            return ashwinsheth_qhse_8082_service.uploadSignature(userId,token,file);
        }

        // NCR API's

        @RequestMapping(value = "/qc/ncr", method = RequestMethod.POST)
        public ResponseEntity<Object> createQcNcr(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ncrRequest)
        {
            return ashwinsheth_qhse_8082_service.createQcNcr(userId, token,ncrRequest);
        }

        @RequestMapping(value = "/qc/ncr", method = RequestMethod.GET)
        public ResponseEntity<Object> getNcr( @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
        @RequestParam(value = "companyId") long companyId,
        @RequestParam(value = "projectId") int projectId,
        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
        @RequestParam(value = "pageSize", defaultValue = "100") int pageSize)

        {
            return ashwinsheth_qhse_8082_service.getNcr(userId, token,companyId,projectId,pageNum,pageSize);
        }

        @RequestMapping(value = "/qc/ncrDetails", method = RequestMethod.GET)
        public ResponseEntity<Object> getNcrDetails( @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
        @RequestParam(value = "ncrId") long ncrId)

        {
            return ashwinsheth_qhse_8082_service.getNcrDetails(userId, token,ncrId);
        }

        // Safety TBT API's

        @PostMapping(value = "/SafetyTBT")
        ResponseEntity<Object> saveSafetyTBT(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object safetyTBTRequest)
        {
            return ashwinsheth_qhse_8082_service.saveSafetyTBT(userId,token,safetyTBTRequest);
        }

        @GetMapping(value = "/SafetyTBT")
        ResponseEntity<List<Object>> getSafetyTBT(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
        @RequestParam(value = "projectId", required = false, defaultValue = "0") int projectId)
        {
            return ashwinsheth_qhse_8082_service.getSafetyTBT(userId,token,projectId);
        }

        @GetMapping(value = "/getSafetyTbtById/{id}")
        ResponseEntity<Object> getSafetyTbtById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "id") Integer tbtId)
        {
            return ashwinsheth_qhse_8082_service.getSafetyTbtById(userId,token,tbtId);
        }

        // safety controller API's

        // safety observation API's
        @PostMapping(value = "/safety/obs/create")
        public ResponseEntity<Object> createSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object obsRequest) {
        return ashwinsheth_qhse_8082_service.createSafetyObservation(userId, token, obsRequest);
    }

        @PostMapping(value = "/safety/obs/update")
        public ResponseEntity<Object> updateSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object updateRequest) {
        return ashwinsheth_qhse_8082_service.updateSafetyObservation(userId, token, updateRequest);
    }

        @PostMapping(value = "/safety/obs/find")
        public ResponseEntity<Object> findSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync,
            @RequestBody Object findRequest) {
        return ashwinsheth_qhse_8082_service.findSafetyObservation(userId, token, lastSync, findRequest);
    }

        @GetMapping(value = "/safety/obs/find/{obsId}")
        public ResponseEntity<Object> findSafetyObservationByObsId(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "obsId") Integer obsId) {
        return ashwinsheth_qhse_8082_service.findSafetyObservationByObsId(userId, token, obsId);
    }

        @PostMapping(value = "/safety/obs/history/find")
        public ResponseEntity<Object> findObservationRequestHistory(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object historyRequest) {
        return ashwinsheth_qhse_8082_service.findObservationRequestHistory(userId, token, historyRequest);
    }

        // MASTER API

        @GetMapping(value = "/safety/master/unsafeAct/findAll")
        public ResponseEntity<Object> findAllUnsafeAct(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return ashwinsheth_qhse_8082_service.findAllUnsafeAct(userId, token, lastSync);
    }

        @GetMapping(value = "/safety/master/unsafeCondition/findAll")
        public ResponseEntity<Object> findAllUnsafeCondition(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return ashwinsheth_qhse_8082_service.findAllUnsafeCondition(userId, token, lastSync);
    }

        @GetMapping(value = "/safety/master/typeOfWork/findAll")
        public ResponseEntity<Object> findAllTypeOfWork(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return ashwinsheth_qhse_8082_service.findAllTypeOfWork(userId, token, lastSync);
    }

        @GetMapping(value = "/safety/master/checklistQuestions/findAll")
        public ResponseEntity<Object> findAllChecklistQuestions(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync)
        {
            return ashwinsheth_qhse_8082_service.findAllChecklistQuestions(userId, token, lastSync);
        }

        @GetMapping(value = "/safety/master/typeOfWorkChecklistMapping")
        public ResponseEntity<Object> findAllTypeOfWorkChecklistMapping(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync)
        {
            return ashwinsheth_qhse_8082_service.findAllTypeOfWorkChecklistMapping(userId, token, lastSync);
        }

        @RequestMapping(value = "/rest/v1/getRoleMaster", method = RequestMethod.GET)
        ResponseEntity<Object> getRoleMaster()
        {
            return ashwinsheth_qhse_8082_service.getRoleMaster();
        }

        // FOR PTW API

        @PostMapping(value = "/safety/ptw")
        public ResponseEntity<Object> createSafetyPTW(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ptwRequest) {
        return ashwinsheth_qhse_8082_service.createSafetyPTW(userId, token, ptwRequest);
    }

        @PutMapping(value = "/safety/ptw")
        public ResponseEntity<Object> updateSafetyPTW(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ptwRequest) {
        return ashwinsheth_qhse_8082_service.updateSafetyPTW(userId, token, ptwRequest);
    }

        @PutMapping(value = "/safety/ptwStatus")
        public ResponseEntity<Object> updatePTWStatus(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object request) {
        return ashwinsheth_qhse_8082_service.updatePTWStatus(userId, token, request);
    }

        @PutMapping(value = "/safety/ptwClose")
        public ResponseEntity<Object> updateUnclosedPTW() {
        return ashwinsheth_qhse_8082_service.updateUnclosedPTW();
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
        return ashwinsheth_qhse_8082_service.findSafetyPTW(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
    }

        @GetMapping(value = "/safety/ptw/findsp")
        public ResponseEntity<Object> findSafetyPTWSP(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "page_num", defaultValue = "1") Integer page,
            @RequestParam(value = "page_size", defaultValue = "500") Integer pageSize,
            @RequestParam(value = "project_id") Integer projectId) {
        return ashwinsheth_qhse_8082_service.findSafetyPTWSP(userId, token, page, pageSize, projectId);
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
        return ashwinsheth_qhse_8082_service.findSafetyPTWW(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
    }

        @GetMapping(value = "/safety/ptw/find/{ptwId}")
        public ResponseEntity<Object> findSafetyPTWById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "ptwId") Integer ptwId) {
        return ashwinsheth_qhse_8082_service.findSafetyPTWById(userId, token, ptwId);
    }

        @GetMapping(value = "/safety/ptw/find/V2/{ptwId}")
        public ResponseEntity<Object> findPTWById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "ptwId") Integer ptwId) {
        return ashwinsheth_qhse_8082_service.findPTWById(userId, token, ptwId);
    }

        @GetMapping(value = "/safety/findPtwByFilter")
        public ResponseEntity<Object> findPTWByFilter(
            @RequestParam(value = "fromDate") String fromDate,
            @RequestParam(value = "toDate") String toDate,
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token) {
        return ashwinsheth_qhse_8082_service.findPTWByFilter(fromDate, toDate, userId, token);
    }

        @PostMapping(value = "/safety/ptw/history/find")
        public ResponseEntity<Object> findPTWHistory(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ptwHistoryFindRequest) {
        return ashwinsheth_qhse_8082_service.findPTWHistory(userId, token, ptwHistoryFindRequest);
    }

        @PostMapping(value = "/safety/findPtwCount")
        public ResponseEntity<Object> findPtwCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return ashwinsheth_qhse_8082_service.findPtwCount(userId, token, dashboardCountRequest);
    }

        @PostMapping(value = "/safety/findObsCount")
        public ResponseEntity<Object> findObsCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return ashwinsheth_qhse_8082_service.findObsCount(userId, token, dashboardCountRequest);
    }

        @PostMapping(value = "/safety/findEcCount")
        public ResponseEntity<Object> findEcCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return ashwinsheth_qhse_8082_service.findEcCount(userId, token, dashboardCountRequest);
    }

        @PostMapping(value = "/safety/findTbtCount")
        public ResponseEntity<Object> findTbtCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest) {
        return ashwinsheth_qhse_8082_service.findTbtCount(userId, token, dashboardCountRequest);
    }

        @PostMapping(value = "/safety/uploadMultipleFiles", consumes = "multipart/form-data")
        public ResponseEntity<Object> uploadMultipleFiles(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam("eventId") Integer eventId,
            @RequestParam("eventName") String eventName,
            @RequestPart("file") MultipartFile[] files) {
        return ashwinsheth_qhse_8082_service.uploadMultipleFiles(userId, token, eventId, eventName, files);
    }

        @PostMapping(value = "/safety/uploadCheckListMedia", consumes = "multipart/form-data")
        public ResponseEntity<Object> uploadCheckListMedia(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam("checklistAnswerId") Integer checklistAnswerId,
            @RequestPart("file") MultipartFile[] files) {
        return ashwinsheth_qhse_8082_service.uploadCheckListMedia(userId, token, checklistAnswerId, files);
    }

        @GetMapping(value = "/safety/findUsersByProjectIdAndRoleId")
        public ResponseEntity<Object> findUsersByProjectIdAndRoleId(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "project_id") Integer projectId,
            @RequestParam(value = "roleId") Integer roleId,
            @RequestParam(value = "lastSync", required = false) String lastSync) {
        return ashwinsheth_qhse_8082_service.findUsersByProjectIdAndRoleId(userId, token, projectId, roleId, lastSync);
    }

        @GetMapping(value = "/safety/sendOBSNotification")
        public ResponseEntity<Object> sendOBSNotification(
            @RequestHeader(value = "level1LocationId") Integer level1LocationId,
            @RequestHeader(value = "obsId") Integer obsId,
            @RequestHeader(value = "user_id") Integer userId) {
        return ashwinsheth_qhse_8082_service.sendOBSNotification(level1LocationId, obsId, userId);
    }

        // Equipment

        @PostMapping(value = "/safety/equipment")
        public ResponseEntity<Object> saveEquipment(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object equipmentRequest) {
        return ashwinsheth_qhse_8082_service.saveEquipment(userId, token, equipmentRequest);
    }

        @PutMapping(value = "/safety/equipment")
        public ResponseEntity<Object> updateEquipment(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object equipmentRequest) {
        return ashwinsheth_qhse_8082_service.updateEquipment(userId, token, equipmentRequest);
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
        return ashwinsheth_qhse_8082_service.findSafetyEquipment(userId, token, page, pageSize, projectId, lastSync, locationLevel1Id);
    }

        @PostMapping(value = "/safety/equipment/history/find")
        public ResponseEntity<Object> findEQHistory(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object eqHistoryFindRequest) {
        return ashwinsheth_qhse_8082_service.findEQHistory(userId, token, eqHistoryFindRequest);
    }

        @GetMapping(value = "/safety/equipment/find/{equipmentId}")
        public ResponseEntity<Object> findSafetyEquipmentById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "equipmentId") Integer equipmentId) {
        return ashwinsheth_qhse_8082_service.findSafetyEquipmentById(userId, token, equipmentId);
    }

        @GetMapping(value = "/safety/findOBSByFilter")
        public ResponseEntity<Object> findOBSByFilter(
            @RequestParam(value = "fromDate") String fromDate,
            @RequestParam(value = "toDate") String toDate,
            @RequestParam(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token) {
        return ashwinsheth_qhse_8082_service.findOBSByFilter(fromDate, toDate, userId, token);
    }

        @GetMapping(value = "/safety/statistics/{projectId}")
        public ResponseEntity<Object> findSafetyStatistics(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "projectId") Integer projectId) {
        return ashwinsheth_qhse_8082_service.findSafetyStatistics(userId, token, projectId);
    }

        @PostMapping(value = "/safety/bulletin")
        public ResponseEntity<Object> sendBulletin(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object bulletinRequest) {
        return ashwinsheth_qhse_8082_service.sendBulletin(userId, token, bulletinRequest);
    }

        @GetMapping(value = "/safety/bulletin/{projectId}")
        public ResponseEntity<Object> findBulletinByProjectId(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "projectId") Integer projectId) {
        return ashwinsheth_qhse_8082_service.findBulletinByProjectId(userId, token, projectId);
    }

        @GetMapping(value = "/safety/getSafetyDigitalLibrary")
        public ResponseEntity<Object> getSafetyDigitalLibrary(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
        @RequestParam(value = "project_id") int projectId) {
        return ashwinsheth_qhse_8082_service.getSafetyDigitalLibrary(userId, token, projectId);
    }

        @GetMapping(value = "/safety/ptw/getPendingPtw")
        public ResponseEntity<Object> getPendingPtw(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
        @RequestParam(value = "projectId") int projectId) {
        return ashwinsheth_qhse_8082_service.getPendingPtw(userId, token, projectId);
    }

        @GetMapping(value = "/safety/findCompanyUsers")
        public ResponseEntity<Object> findCompanyUsers(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token) {
        return ashwinsheth_qhse_8082_service.findCompanyUsers(userId, token);
    }

        @GetMapping(value = "/safety/obsReport")
        public ResponseEntity<Object> getDataForObsReport(
        @RequestParam(value = "user_id") int userId,
        @RequestParam(value = "token") String token,
        @RequestParam(value = "obsId") int obsId,
        @RequestParam(value = "webCall", defaultValue = "false") boolean webCall) {

        return ashwinsheth_qhse_8082_service.getDataForObsReport(userId, token, obsId, webCall);
    }

        // Emergency helpline

        @PostMapping(value = "/addEmergencyHelpline")
        public ResponseEntity<Object> addEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object emergencyHelplineRequest)
        {
            return ashwinsheth_qhse_8082_service.addEmergencyHelpLine(userId,token,emergencyHelplineRequest);
        }

        @GetMapping(value = "/getEmergencyHelpline")
        public ResponseEntity<List<Object>> getAllEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
        int projectId)
        {
            return ashwinsheth_qhse_8082_service.getAllEmergencyHelpLine(userId,token,projectId);
        }

        @PutMapping(value = "/updateEmergencyHelpline")
        public ResponseEntity<Object> updateEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object emergencyHelplineRequest)
        {
            return ashwinsheth_qhse_8082_service.updateEmergencyHelpLine(userId,token,emergencyHelplineRequest);
        }

        @PutMapping(value = "/deleteEmergencyHelpline")
        public ResponseEntity<Object> deleteEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
        @RequestParam(value = "id") int id)

        {
            return ashwinsheth_qhse_8082_service.deleteEmergencyHelpLine(userId,token,id);
        }

        // Hazards

        @PostMapping(value = "/hazards/upload/{projectId}", consumes = "multipart/form-data")
        public ResponseEntity<Object> uploadHazards(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "projectId") Integer projectId,
            @RequestParam(value = "file") MultipartFile file)
        {
            return ashwinsheth_qhse_8082_service.uploadHazards(userId,token,projectId,file);
        }

        @GetMapping(value = "/getHazardsByProjectId/{project_id}")
        public ResponseEntity<List<Object>> getHazardsByProjectId(@RequestHeader(value = "user_id") Integer userId,
            @RequestHeader(value = "token") String token,
        @PathVariable(value = "project_id") int projectId)
        {
            return ashwinsheth_qhse_8082_service.getHazardsByProjectId(userId,token,projectId);
        }

        // Location master

        @RequestMapping(value = "/location/db/find", method = RequestMethod.POST)
        public ResponseEntity<Map<String, Object>> findLocation(@RequestBody String data,
            @RequestParam(value = "lastSync", required = false) String lastSync)
        {
            return ashwinsheth_qhse_8082_service.findLocation(data,lastSync);
        }

        @RequestMapping(value = "/rest/v1/location/db/findall", method = RequestMethod.GET)
        public ResponseEntity<Map<String, Object>> getAllLocationsFromDB(
        @RequestParam(value = "project_id", required = true) int pid,
        @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
        @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
        HttpServletRequest request,
        @RequestParam("user_id") int user_id,
        @RequestParam("token") String token)
        {
            return ashwinsheth_qhse_8082_service.getAllLocationsFromDB(pid,page,pageSize,request,user_id,token);
        }

        @GetMapping(value = "/getLocationLevel1ByProjectId")
        public ResponseEntity<List<Object>> getLocationLevel1ByProjectId(@RequestParam(value = "projectId") int projectId)
        {
            return ashwinsheth_qhse_8082_service.getLocationLevel1ByProjectId(projectId);
        }

        @RequestMapping(value = "/addLocations", consumes = "multipart/form-data", method = RequestMethod.POST)
        public String addLocations(@RequestParam(value = "userId") int userId,
        @RequestParam(value = "file") MultipartFile file,
        @RequestParam(value = "projectId") int projectId)
        {
            return ashwinsheth_qhse_8082_service.addLocations(userId,file,projectId);
        }

        @RequestMapping(value = "/checklistDataUpload", consumes = "multipart/form-data", method = RequestMethod.POST)
        public Map<String, Integer> checklistDataUpload(
            @RequestParam(value = "file") MultipartFile file,
        @RequestParam(value = "userId") int userId)
        {
            return ashwinsheth_qhse_8082_service.checklistDataUpload(file,userId);
        }

        @RequestMapping(value = "/rest/v1/observation/master/db/findall", method = RequestMethod.GET)
        public ResponseEntity<Object> getAllObservationsFromDB(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
        @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
        HttpServletRequest request,
        @RequestHeader(value = "userId") Integer userId,
        @RequestHeader(value = "token") String token,
        @RequestParam(value = "lastSync", required = false) String lastSync)
        {
            return ashwinsheth_qhse_8082_service.getAllObservationsFromDB(page,pageSize,request,userId,token,lastSync);
        }

        // Progress Report

        @PostMapping(value = "/progressReport")
        public ResponseEntity<Object> saveProgressReport(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object progressReportRequest)
        {
            return ashwinsheth_qhse_8082_service.saveProgressReport(userId,token,progressReportRequest);
        }

        @GetMapping(value = "/progressReport")
        public ResponseEntity<List<Object>> getProgressReport(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token)

        {
            return ashwinsheth_qhse_8082_service.getProgressReport(userId,token);
        }

        @GetMapping(value = "/progressReport/{id}")
        public ResponseEntity<Object> getProgressReportById(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
        @PathVariable(value = "id") long progressReportId)
        {
            return ashwinsheth_qhse_8082_service.getProgressReportById(userId,token,progressReportId);
        }

        // Projects

        @RequestMapping(value = "/rest/v1/projects", method = RequestMethod.GET)
        public ResponseEntity<Map<String, Object>> restProjects(@RequestParam("user_id") int user_id,
        @RequestParam("token") String token,
        @RequestParam(value = "lastSync", required = false) String lastSync) throws JsonParseException, JsonMappingException, IOException

        {
            return ashwinsheth_qhse_8082_service.restProjects(user_id,token,lastSync);
        }

        @RequestMapping(value = "/project", method = RequestMethod.POST)
        public Object addProject(@RequestHeader("userId") int userId,
        @RequestHeader("token") String token,
        @RequestBody Object projectCreateRequest)
        {
            return ashwinsheth_qhse_8082_service.addProject(userId,token,projectCreateRequest);
        }

        @RequestMapping(value = "/project", method = RequestMethod.PUT)
        public Object updateProject(@RequestHeader("userId") int userId,
        @RequestHeader("token") String token,
        @RequestBody Object projectUpdateRequest)
        {
            return ashwinsheth_qhse_8082_service.updateProject(userId,token,projectUpdateRequest);
        }

        @RequestMapping(value = "/project", method = RequestMethod.GET)
        public List<Object> getProjects(@RequestHeader("userId") int userId,
        @RequestHeader("token") String token)
        {
            return ashwinsheth_qhse_8082_service.getProjects(userId,token);
        }

        // QC controller

        @RequestMapping(value = "/crfi", method = RequestMethod.POST)
        public ResponseEntity<Object> createCRFI(@RequestHeader(value = "userId") int userId,
        @RequestHeader(value = "token") String token,
        @RequestBody Object activityInspectionRequest)
        {
            return ashwinsheth_qhse_8082_service.createCRFI(userId,token,activityInspectionRequest);
        }

        @RequestMapping(value = "/crfi", method = RequestMethod.PUT)
        public ResponseEntity<Object> updateCRFI(@RequestHeader(value = "userId") int userId,
        @RequestHeader(value = "token") String token,
        @RequestBody Object activityInspectionUpdateRequest)
        {
            return ashwinsheth_qhse_8082_service.updateCRFI(userId,token,activityInspectionUpdateRequest);
        }

        @RequestMapping(value = "/crfi", method = RequestMethod.GET)
        public ResponseEntity<List<Object>> getCRFI(@RequestHeader(value = "userId") int userId,
        @RequestHeader(value = "token") String token,
        @RequestParam(value = "crfiId") long crfiId,
        @RequestParam(value = "projectId") long projectId)
        {
            return ashwinsheth_qhse_8082_service.getCRFI(userId,token,crfiId,projectId);
        }

        @RequestMapping(value = "/crfiDetails", method = RequestMethod.GET)
        public ResponseEntity<Object> getCRFIDetails(@RequestHeader(value = "userId") int userId,
        @RequestHeader(value = "token") String token,
        @RequestParam(value = "crfiId") long crfiId)
        {
            return ashwinsheth_qhse_8082_service.getCRFIDetails(userId,token,crfiId);
        }

        @RequestMapping(value = "/crfiReport", method = RequestMethod.GET)
        public ResponseEntity<?> getDataForRFIReport(@RequestParam(value = "user_id") int userId,
        @RequestParam(value = "token") String token,
        @RequestParam(value = "crfiId",required = true) int crfiId,
        @RequestParam(value = "webCall", defaultValue = "false", required = false) boolean webCall)
        {
            return ashwinsheth_qhse_8082_service.getDataForRFIReport(userId,token,crfiId,webCall);
        }

        @RequestMapping(value = "/filter/crfi", method = RequestMethod.POST)
        public ResponseEntity<Object> getObsFilter(@RequestHeader(value = "userId") int userId,
        @RequestHeader(value = "token") String token,
        @RequestBody Object filterCrfiRequest)
        {
            return ashwinsheth_qhse_8082_service.getObsFilter(userId,token,filterCrfiRequest);
        }

        // QC OBS API

        @RequestMapping(value = "/obs", method = RequestMethod.POST)
        public ResponseEntity<Object> createQCObservation(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object obsRequest)
        {
            return ashwinsheth_qhse_8082_service.createQCObservation(userId,token,obsRequest);
        }

        @RequestMapping(value = "/obs", method = RequestMethod.GET)
        public ResponseEntity<List<Object>> getObs(@RequestHeader(value = "userId") int userId,
        @RequestHeader(value = "token") String token,
        @RequestParam(value = "obsId") long obsId,
        @RequestParam(value = "projectId") long projectId)
        {
            return ashwinsheth_qhse_8082_service.getObs(userId,token,obsId,projectId);
        }


        @RequestMapping(value = "/obs", method = RequestMethod.PUT)
        public ResponseEntity<Object> updateOBS(@RequestHeader(value = "userId") int userId,
        @RequestHeader(value = "token") String token,
        @RequestBody Object obsUpdateRequest)
        {
            return ashwinsheth_qhse_8082_service.updateOBS(userId,token,obsUpdateRequest);
        }

        @RequestMapping(value = "/obsDetails", method = RequestMethod.GET)
        public ResponseEntity<Object> getOBSDetails(@RequestHeader(value = "userId") int userId,
        @RequestHeader(value = "token") String token,
        @RequestParam(value = "obsId") long obsId)
        {
            return ashwinsheth_qhse_8082_service.getOBSDetails(userId,token,obsId);
        }

        // Report controller

        @RequestMapping(value = "/rorReport", method = RequestMethod.GET)
        public void rorReport()
        {
            ashwinsheth_qhse_8082_service.rorReport();
        }

        @RequestMapping(value = "/ptwReport", method = RequestMethod.POST)
        public void ptwReport(@RequestBody Object ptwReportRequest)
        {
            ashwinsheth_qhse_8082_service.ptwReport(ptwReportRequest);
        }

        @RequestMapping(value = "/ptwReportDownload", method = RequestMethod.GET)
        public ResponseEntity<Object> ptwReportDownload(
        @RequestParam(value = "user_id") int userId,
        @RequestParam(value = "token") String token,
        @RequestParam(value = "ptwId",required = true) int ptwId,
        @RequestParam(value = "webCall", defaultValue = "true", required = false) boolean webCall)
        {
            return ashwinsheth_qhse_8082_service.ptwReportDownload(userId,token,ptwId,webCall);
        }

        @RequestMapping(value = "/equipmentReportDownload", method = RequestMethod.GET)
        public ResponseEntity<Object> equipmentReportDownload(
        @RequestParam(value = "user_id") int userId,
        @RequestParam(value = "token") String token,
        @RequestParam(value = "equipmentId") int equipmentId,
        @RequestParam(value = "webCall", defaultValue = "true", required = false) boolean webCall)
        {
            return ashwinsheth_qhse_8082_service.equipmentReportDownload(userId,token,equipmentId,webCall);
        }

        @RequestMapping(value = "/safetyObsReport", method = RequestMethod.GET)
        public void safetyObsReport()
        {
            ashwinsheth_qhse_8082_service.safetyObsReport();
        }

        @RequestMapping(value = "/escalateOBSReport", method = RequestMethod.GET)
        public void escalateOBSReport()
        {
            ashwinsheth_qhse_8082_service.escalateOBSReport();
        }

        @RequestMapping(value = "/safetyObsReportByFilter", method = RequestMethod.GET)
        public ResponseEntity<Object> safetyObsReportByFilter(@RequestParam(value = "fromDate") String fromDate,
            @RequestParam(value = "toDate") String toDate,
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId)
        {
            return ashwinsheth_qhse_8082_service.safetyObsReportByFilter(fromDate,toDate,userId,projectId);
        }

        @GetMapping("/downloadMemoPdf")
        public ResponseEntity<Object> downloadMemoPdf(@RequestHeader("user_id") int user_id,
        @RequestHeader("token") String token,
        @RequestParam(name = "obsId", required = false, defaultValue = "0") int obsId)
        {
            return ashwinsheth_qhse_8082_service.downloadMemoPdf(user_id,token,obsId);
        }


        @GetMapping("/downloadDebitPdf")
        public ResponseEntity<Object> downloadDebitPdf(@RequestHeader("user_id") int user_id,
        @RequestHeader("token") String token,
        @RequestParam(name = "debitId", required = false, defaultValue = "0") int debitId)
        {
            return ashwinsheth_qhse_8082_service.downloadDebitPdf(user_id,token,debitId);
        }


        @GetMapping("/downloadIncidentPdf")
        public ResponseEntity<Object> downloadIncidentPdf(@RequestHeader("user_id") int user_id,
        @RequestHeader("token") String token,
        @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId)
        {
            return ashwinsheth_qhse_8082_service.downloadIncidentPdf(user_id,token,incidentId);
        }


        @GetMapping("/sendIncidentPdf")
        public ResponseEntity<Object> sendIncidentPdf(@RequestHeader("user_id") int user_id,
        @RequestHeader("token") String token,
        @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId)
        {
            return ashwinsheth_qhse_8082_service.sendIncidentPdf(user_id,token,incidentId);
        }


        @GetMapping("/downloadTBTPdf")
        public ResponseEntity<Object> downloadTBTPdf(@RequestHeader("user_id") int user_id,
        @RequestHeader("token") String token,
        @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId)
        {
            return ashwinsheth_qhse_8082_service.downloadTBTPdf(user_id,token,tbtId);
        }

        @RequestMapping(value = "/equipmentReport", method = RequestMethod.POST)
        public ResponseEntity<Object> equipmentReport(@RequestBody Object equipmentReportRequest)
        {
            return ashwinsheth_qhse_8082_service.equipmentReport(equipmentReportRequest);
        }

        @RequestMapping(value = "/obsreport", method = RequestMethod.GET)
        public ResponseEntity<Map<String, Object>> getObsReport(@RequestParam(value = "user_id") int userId,
        @RequestParam(value = "token") String token,
        @RequestParam(value = "obsId",required = false) int obsId)
        {
            return ashwinsheth_qhse_8082_service.getObsReport(userId,token,obsId);
        }


        @RequestMapping(value = "/weeklyStatusReport", method = RequestMethod.GET)
        public void weeklyStatusReport(@RequestParam(value = "projectId") int projectId,
        @RequestParam(value = "fromDate") String fromDate,
        @RequestParam(value = "toDate") String toDate,
        @RequestParam(value = "locationBased") int locationBased)
        {
            ashwinsheth_qhse_8082_service.weeklyStatusReport(projectId,fromDate,toDate,locationBased);
        }

        @GetMapping("/downloadWorkerPdf")
        public ResponseEntity<Object> downloadWorkerPdf(@RequestParam("user_id") int user_id,
        @RequestParam("token") String token,
        @RequestParam(name = "workerId", required = true, defaultValue = "0") int workerId,
        @RequestParam(value = "webCall", defaultValue = "true", required = false) boolean webCall)
        {
            return ashwinsheth_qhse_8082_service.downloadWorkerPdf(user_id,token,workerId,webCall);
        }


        // Incident controller

        @RequestMapping(value = "/incident", method = RequestMethod.POST)
        public ResponseEntity<Object> saveIncident(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object request)
        {
            return ashwinsheth_qhse_8082_service.saveIncident(userId,token,request);
        }


        @RequestMapping(value = "/incident", method = RequestMethod.GET)
        public ResponseEntity<Object> findSafetyIncident(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
            @RequestParam(value = "project_id") Integer projectId)
        {
            return ashwinsheth_qhse_8082_service.findSafetyIncident(userId,token,page,pageSize,projectId);
        }

        @RequestMapping(value = "/incident/{incidentId}", method = RequestMethod.GET)
        public ResponseEntity<Object> findIncidentById(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "incidentId") Integer incidentId)
        {
            return ashwinsheth_qhse_8082_service.findIncidentById(userId,token,incidentId);
        }

        // Safety worker controller

        @PostMapping(value = "/SafetyWorkers")
        public ResponseEntity<Object> saveSafetyWorkers(@RequestHeader(value = "user_id") int userId,
        @RequestHeader(value = "token") String token,
        @RequestBody Object safetyWorkersRequest)
        {
            return ashwinsheth_qhse_8082_service.saveSafetyWorkers(userId,token,safetyWorkersRequest);
        }

        @GetMapping(value = "/SafetyWorkers")
        public ResponseEntity<List<Object>> getSafetyWorkers(@RequestHeader(value = "user_id") int userId,
        @RequestHeader(value = "token") String token,
        @RequestParam(value = "project_id") long projectId)
        {
            return ashwinsheth_qhse_8082_service.getSafetyWorkers(userId,token,projectId);
        }

        @GetMapping(value = "/SafetyWorkers/{id}")
        public ResponseEntity<Object> getSafetyWorkersById(@RequestHeader(value = "user_id") int userId,
        @RequestHeader(value = "token") String token,
        @RequestParam(value = "id") long workerId)
        {
            return ashwinsheth_qhse_8082_service.getSafetyWorkersById(userId,token,workerId);
        }

        @PutMapping(value = "/updateSafetyWorker/{id}")
        public ResponseEntity<Object> updateSafetyWorkerById(@RequestHeader(value = "user_id") int userId,
        @RequestHeader(value = "token") String token,
        @RequestParam(value = "id") long workerId,
        @RequestBody Object request)
        {
            return ashwinsheth_qhse_8082_service.updateSafetyWorkerById(userId,token,workerId,request);
        }

        // Unit Master

        @GetMapping(value = "/unitMaster")
        public ResponseEntity<List<Object>> getAllUnitMaster(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token)
        {
            return ashwinsheth_qhse_8082_service.getAllUnitMaster(userId,token);
        }

        @GetMapping(value = "/unitMaster/{id}")
        public ResponseEntity<Object> getUnitMasterById(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
        @PathVariable(value = "id") long unitMasterId)
        {
            return ashwinsheth_qhse_8082_service.getUnitMasterById(userId,token,unitMasterId);
        }

        // User controller

        @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
        public ResponseEntity<Object> addUser(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object userRequestModel)
        {
            return ashwinsheth_qhse_8082_service.addUser(userId,token,userRequestModel);
        }

        @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json"})
        public ResponseEntity<Object> UpdateUser(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object userRequestModel)
        {
            return ashwinsheth_qhse_8082_service.UpdateUser(userId,token,userRequestModel);
        }

        @RequestMapping(value = "/fetch", method = RequestMethod.GET, produces = {"application/json"})
        public ResponseEntity<List<Object>> getUsers(@RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
        @RequestParam(value = "projectId", required = false, defaultValue = "0") int projectId,
        @RequestParam(value = "companyId", required = false, defaultValue = "0") int companyId,
        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
        @RequestParam(value = "pageSize", defaultValue = "1000") int pageSize)
        {
            return ashwinsheth_qhse_8082_service.getUsers(userId,token,projectId,companyId,pageNum,pageSize);
        }

        @GetMapping(value = "/getNonProjectUsers")
        public ResponseEntity<List<Object>> getNonProjectUsers(@RequestParam(value = "projectId") int projectId,
        @RequestParam(value = "companyId") int companyId)
        {
            return ashwinsheth_qhse_8082_service.getNonProjectUsers(projectId,companyId);
        }

        @RequestMapping(value = "/userMapping", method = RequestMethod.POST)
        public ResponseEntity<Object> userMapping(@RequestBody Object userMappingRequest)
        {
            return ashwinsheth_qhse_8082_service.userMapping(userMappingRequest);
        }

        @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = {"application/json"})
        public ResponseEntity<Object> deleteUser(@RequestParam(value = "user_id") Integer userId)
        {
            return ashwinsheth_qhse_8082_service.deleteUser(userId);
        }


    }
