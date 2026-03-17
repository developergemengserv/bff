package com.gemengserv.bff.service;

import com.gemengserv.bff.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@FeignClient(name = "tejraj", url = "http://localhost:8081", configuration = FeignConfig.class)
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


    // NCR API's

    @RequestMapping(value = "/qc/ncr", method = RequestMethod.POST)
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

// Safety TBT API's

    @PostMapping(value = "/SafetyTBT")
    ResponseEntity<Object> saveSafetyTBT(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object safetyTBTRequest);

    @GetMapping(value = "/SafetyTBT")
    ResponseEntity<List<Object>> getSafetyTBT(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "projectId", required = false, defaultValue = "0") int projectId);

    @GetMapping(value = "/getSafetyTbtById/{id}")
    ResponseEntity<Object> getSafetyTbtById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "id") Integer tbtId);

    // safety controller API's

    // safety observation API's

    @PostMapping(value = "/safety/obs/create")
    ResponseEntity<Object> createSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object obsRequest);

    @PostMapping(value = "/safety/obs/update")
    ResponseEntity<Object> updateSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object updateRequest);

    @PostMapping(value = "/safety/obs/find")
    ResponseEntity<Object> findSafetyObservation(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync,
            @RequestBody Object findRequest);

    @GetMapping(value = "/safety/obs/find/{obsId}")
    ResponseEntity<Object> findSafetyObservationByObsId(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "obsId") Integer obsId);

    @PostMapping(value = "/safety/obs/history/find")
    ResponseEntity<Object> findObservationRequestHistory(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object historyRequest);


    // MASTER API

    @GetMapping(value = "/safety/master/unsafeAct/findAll")
    ResponseEntity<Object> findAllUnsafeAct(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync);

    @GetMapping(value = "/safety/master/unsafeCondition/findAll")
    ResponseEntity<Object> findAllUnsafeCondition(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync);

    @GetMapping(value = "/safety/master/typeOfWork/findAll")
    ResponseEntity<Object> findAllTypeOfWork(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync);

    @GetMapping(value = "/safety/master/checklistQuestions/findAll")
    ResponseEntity<Object> findAllChecklistQuestions(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync);

    @GetMapping(value = "/safety/master/typeOfWorkChecklistMapping")
    ResponseEntity<Object> findAllTypeOfWorkChecklistMapping(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "lastSync", required = false) String lastSync);

// FOR PTW API
@PostMapping(value = "/safety/ptw")
ResponseEntity<Object> createSafetyPTW(
        @RequestHeader(value = "userId") Integer userId,
        @RequestHeader(value = "token") String token,
        @RequestBody Object ptwRequest);

    @PutMapping(value = "/safety/ptw")
    ResponseEntity<Object> updateSafetyPTW(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ptwRequest);

    @PutMapping(value = "/safety/ptwStatus")
    ResponseEntity<Object> updatePTWStatus(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object request);

    @PutMapping(value = "/safety/ptwClose")
    ResponseEntity<Object> updateUnclosedPTW();

    @GetMapping(value = "/safety/ptw/find")
    ResponseEntity<Object> findSafetyPTW(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "page_num") Integer page,
            @RequestParam(value = "page_size") Integer pageSize,
            @RequestParam(value = "project_id") Integer projectId,
            @RequestParam(value = "lastSync", required = false) String lastSync,
            @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id);

    @GetMapping(value = "/safety/ptw/findsp")
    ResponseEntity<Object> findSafetyPTWSP(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "page_num") Integer page,
            @RequestParam(value = "page_size") Integer pageSize,
            @RequestParam(value = "project_id") Integer projectId);

    @GetMapping(value = "/safety/ptw/find/V2")
    ResponseEntity<Object> findSafetyPTWW(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "page_num") Integer page,
            @RequestParam(value = "page_size") Integer pageSize,
            @RequestParam(value = "project_id") Integer projectId,
            @RequestParam(value = "lastSync", required = false) String lastSync,
            @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id);

    @GetMapping(value = "/safety/ptw/find/{ptwId}")
    ResponseEntity<Object> findSafetyPTWById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "ptwId") Integer ptwId);

    @GetMapping(value = "/safety/ptw/find/V2/{ptwId}")
    ResponseEntity<Object> findPTWById(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @PathVariable(value = "ptwId") Integer ptwId);

    @GetMapping(value = "/safety/findPtwByFilter")
    ResponseEntity<Object> findPTWByFilter(
            @RequestParam(value = "fromDate") String fromDate,
            @RequestParam(value = "toDate") String toDate,
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token);

    @PostMapping(value = "/safety/ptw/history/find")
    ResponseEntity<Object> findPTWHistory(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object ptwHistoryFindRequest);

    @PostMapping(value = "/safety/findPtwCount")
    ResponseEntity<Object> findPtwCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest);

    @PostMapping(value = "/safety/findObsCount")
    ResponseEntity<Object> findObsCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest);

    @PostMapping(value = "/safety/findEcCount")
    ResponseEntity<Object> findEcCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest);

    @PostMapping(value = "/safety/findTbtCount")
    ResponseEntity<Object> findTbtCount(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object dashboardCountRequest);

    @PostMapping(value = "/safety/uploadMultipleFiles", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadMultipleFiles(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam("eventId") Integer eventId,
            @RequestParam("eventName") String eventName,
            @RequestPart("file") MultipartFile[] files);

    @PostMapping(value = "/safety/uploadCheckListMedia", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadCheckListMedia(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam("checklistAnswerId") Integer checklistAnswerId,
            @RequestPart("file") MultipartFile[] files);

    @GetMapping(value = "/safety/findUsersByProjectIdAndRoleId")
    ResponseEntity<Object> findUsersByProjectIdAndRoleId(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "project_id") Integer projectId,
            @RequestParam(value = "roleId") Integer roleId,
            @RequestParam(value = "lastSync", required = false) String lastSync);

    @GetMapping(value = "/safety/sendOBSNotification")
    ResponseEntity<Object> sendOBSNotification(
            @RequestHeader(value = "level1LocationId") Integer level1LocationId,
            @RequestHeader(value = "obsId") Integer obsId,
            @RequestHeader(value = "user_id") Integer userId);

    // Equipment

        @PostMapping(value = "/safety/equipment")
        ResponseEntity<Object> saveEquipment(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object equipmentRequest);

        @PutMapping(value = "/safety/equipment")
        ResponseEntity<Object> updateEquipment(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object equipmentRequest);

        @GetMapping(value = "/safety/equipment/find")
        ResponseEntity<Object> findSafetyEquipment(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestParam(value = "page_num") Integer page,
                @RequestParam(value = "page_size") Integer pageSize,
                @RequestParam(value = "project_id") Integer projectId,
                @RequestParam(value = "lastSync", required = false) String lastSync,
                @RequestParam(value = "locationLevel1Id", required = false) Integer locationLevel1Id);

        @PostMapping(value = "/safety/equipment/history/find")
        ResponseEntity<Object> findEQHistory(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object eqHistoryFindRequest);

        @GetMapping(value = "/safety/equipment/find/{equipmentId}")
        ResponseEntity<Object> findSafetyEquipmentById(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @PathVariable(value = "equipmentId") Integer equipmentId);

        @GetMapping(value = "/safety/findOBSByFilter")
        ResponseEntity<Object> findOBSByFilter(
                @RequestParam(value = "fromDate") String fromDate,
                @RequestParam(value = "toDate") String toDate,
                @RequestParam(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token);

        @GetMapping(value = "/safety/statistics/{projectId}")
        ResponseEntity<Object> findSafetyStatistics(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @PathVariable(value = "projectId") Integer projectId);

        @PostMapping(value = "/safety/bulletin")
        ResponseEntity<Object> sendBulletin(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestBody Object bulletinRequest);

        @GetMapping(value = "/safety/bulletin/{projectId}")
        ResponseEntity<Object> findBulletinByProjectId(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @PathVariable(value = "projectId") Integer projectId);

        @GetMapping(value = "/safety/getSafetyDigitalLibrary")
        ResponseEntity<Object> getSafetyDigitalLibrary(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestParam(value = "project_id") int projectId);

        @GetMapping(value = "/safety/ptw/getPendingPtw")
        ResponseEntity<Object> getPendingPtw(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token,
                @RequestParam(value = "projectId") int projectId);

        @GetMapping(value = "/safety/findCompanyUsers")
        ResponseEntity<Object> findCompanyUsers(
                @RequestHeader(value = "userId") Integer userId,
                @RequestHeader(value = "token") String token);

        @GetMapping(value = "/safety/obsReport")
        ResponseEntity<Object> getDataForObsReport(
                @RequestParam(value = "user_id") int userId,
                @RequestParam(value = "token") String token,
                @RequestParam(value = "obsId") int obsId,
                @RequestParam(value = "webCall") boolean webCall);


}
