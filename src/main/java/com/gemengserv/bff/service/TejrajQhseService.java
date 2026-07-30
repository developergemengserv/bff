package com.gemengserv.bff.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gemengserv.bff.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@FeignClient(name = "tejraj", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface TejrajQhseService {

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

    @GetMapping(value = "/getActivities")
    ResponseEntity<List<Object>> getActivities();

    @RequestMapping(value = "/addActivities", consumes = "multipart/form-data", method = RequestMethod.POST)
    String addActivities(@RequestPart(value = "file") MultipartFile file,
                         @RequestParam(value = "userId") int userId);

    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    public ResponseEntity<Object> addActivity(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestBody Object activityRequest);

    @RequestMapping(value = "/editActivity", method = RequestMethod.POST)
    public ResponseEntity<Object> editActivity(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestBody Object activityRequest);

    @GetMapping(value = "/rest/v1/activityTypeOfWork", produces = "application/json")
    ResponseEntity<Object> getActivityTypeOfWorkMapping(@RequestHeader("user_id") int userId,
                                                        @RequestHeader("token") String token, @RequestParam(value = "lastSync", required = false) String lastSync);

    @RequestMapping(value = "/addActivitiesChecklist", consumes = "multipart/form-data", method = RequestMethod.POST)
    String addLocations(@RequestParam(value = "userId") int userId,
                        @RequestPart(value = "file") MultipartFile file);

    @GetMapping(value = "activityUnitMapping")
    List<Object> getAllActivityUnitMapping();

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    Object addCompany(@RequestHeader("userId") int userId,
                      @RequestHeader("token") String token,
                      @RequestBody Object companyCreateRequest);

    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    Object updateCompany(@RequestHeader("userId") int userId,
                         @RequestHeader("token") String token,
                         @RequestBody Object companyUpdateRequest);

    @RequestMapping(value = "/companyProjectWise", method = RequestMethod.GET)
    public List<Object> getCompaniesProjectWise(@RequestHeader("userId") int userId,
                                                @RequestHeader("token") String token);

    @GetMapping("/company")
    List<Object> getCompanies(@RequestHeader("userId") int userId, @RequestHeader("token") String token);

    @RequestMapping(value = "/deleteCompany", method = RequestMethod.DELETE, produces = {"application/json"})
    ResponseEntity<Object> deleteCompany(@RequestParam(value = "companyId") Integer companyId);

    @RequestMapping(value = "/downloadCompanies", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadCompaniesExcel(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "1000") int pageSize);

    @PostMapping(value = "/registerUser", produces = "application/json")
    ResponseEntity<Object> registerUser(@RequestBody Object userRegisterRequest);

    @PostMapping("/rest/v2/login")
    ResponseEntity<Object> loginAPI(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping(value = "/rest/v1/login", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping(value = "/rest/v1/configuration", method = RequestMethod.GET)
    ResponseEntity<Object> getConfiguration(@RequestParam("package_id") String package_id);

    @RequestMapping(value = "/rest/v1/checkotp", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> checkotp(@RequestParam("otp") int otp, @RequestParam("user_id") int user_id, @RequestParam("version") String version,
                                                 @RequestParam("device_code") String device_code);

    @RequestMapping(value = "/rest/v1/logout", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> logout(@RequestParam("user_id") int user_id, @RequestParam("webCall") boolean webCall);

    @RequestMapping(value = "/rest/v1/userDetails", method = RequestMethod.GET)
    ResponseEntity<Object> userDetails(@RequestParam("user_id") int user_id,
                                       @RequestParam("token") String token);


    @RequestMapping(value = "/rest/v1/media/find", method = RequestMethod.GET)
    void doDownload(@RequestParam("media_url") String mediaUrl, @RequestParam("user_id") int user_id, @RequestParam("token") String token, HttpServletResponse response)
            throws IOException;

    @PostMapping(value = "/rest/v1/upload/signature", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadSignature(@RequestHeader(value = "userId") Integer userId,
                                           @RequestHeader(value = "token") String token,
                                           @RequestPart(value = "file") MultipartFile file);

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
    ResponseEntity<byte[]> getDataForNcrReport(
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

    @RequestMapping(value = "/safety/master/typeOfWork/findAll", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> findAllTypeOfWork(
            @RequestParam(value = "user_id") int userId,
            @RequestParam(value = "token") String token,
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

    @RequestMapping(value = "/rest/v1/getRoleMaster", method = RequestMethod.GET)
    ResponseEntity<Object> getRoleMaster();

    @RequestMapping(value = "/safety/master/answerType/findAll", method = RequestMethod.GET)
    public ResponseEntity<Object> findAllAnswerType(@RequestHeader(value = "userId") Integer userId,
                                                    @RequestHeader(value = "token") String token, @RequestParam(value = "lastSync", required = false) String lastSync);

//    @RequestMapping(value = "/safety/master/checklistQuestions/findAll", method = RequestMethod.GET)
//    public ResponseEntity<Object> findAllChecklistQestions(@RequestHeader(value = "userId") Integer userId,
//                                                           @RequestHeader(value = "token") String token,
//                                                           @RequestParam(value = "lastSync", required = false) String lastSync);

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
    ResponseEntity<byte[]> getDataForObsReport(
            @RequestParam(value = "user_id") int userId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "obsId") int obsId,
            @RequestParam(value = "webCall") boolean webCall);

    // Emergency helpline

    @PostMapping(value = "/addEmergencyHelpline")
    ResponseEntity<Object> addEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestBody Object emergencyHelplineRequest);

    @GetMapping(value = "/getEmergencyHelpline")
    ResponseEntity<List<Object>> getAllEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
                                                         @RequestHeader(value = "token") String token,
                                                         int projectId);

    @PutMapping(value = "/updateEmergencyHelpline")
    ResponseEntity<Object> updateEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @RequestBody Object emergencyHelplineRequest);


    @PutMapping(value = "/deleteEmergencyHelpline")
    ResponseEntity<Object> deleteEmergencyHelpLine(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token,
                                                   @RequestParam(value = "id") int id);

    // Hazards

    @PostMapping(value = "/hazards/upload/{projectId}", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadHazards(@RequestHeader(value = "userId") Integer userId,
                                         @RequestHeader(value = "token") String token,
                                         @PathVariable(value = "projectId") Integer projectId,
                                         @RequestPart(value = "file") MultipartFile file);

    @GetMapping(value = "/getHazardsByProjectId/{project_id}")
    ResponseEntity<List<Object>> getHazardsByProjectId(@RequestHeader(value = "user_id") Integer userId,
                                                       @RequestHeader(value = "token") String token,
                                                       @PathVariable(value = "project_id") int projectId);

    // Location master

    @RequestMapping(value = "/location/db/find", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> findLocation(@RequestBody String data,
                                                     @RequestParam(value = "lastSync", required = false) String lastSync);

    @RequestMapping(value = "/rest/v1/location/db/findall", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllLocationsFromDB(
            @RequestParam(value = "project_id", required = true) int pid,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize, @RequestParam("user_id") int user_id,
            @RequestParam("token") String token);


    @GetMapping(value = "/getLocationLevel1ByProjectId")
    ResponseEntity<List<Object>> getLocationLevel1ByProjectId(@RequestParam(value = "projectId") int projectId);

    @RequestMapping(value = "/addLocations", consumes = "multipart/form-data", method = RequestMethod.POST)
    String addLocations(@RequestParam(value = "userId") int userId,
                        @RequestPart(value = "file") MultipartFile file,
                        @RequestParam(value = "projectId") int projectId);

    @RequestMapping(value = "/checklistDataUpload", consumes = "multipart/form-data", method = RequestMethod.POST)
    Map<String, Integer> checklistDataUpload(
            @RequestPart(value = "file") MultipartFile file,
            @RequestParam(value = "userId") int userId);

    @RequestMapping(value = "/rest/v1/observation/master/db/findall", method = RequestMethod.GET)
    ResponseEntity<Object> getAllObservationsFromDB(@RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
                                                    @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                    @RequestHeader(value = "userId") Integer userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestParam(value = "lastSync", required = false) String lastSync);

    @RequestMapping(value = "/getRelatedLocation", method = RequestMethod.POST)
    public ResponseEntity<Object> getRelatedLocation(@RequestHeader("user_id") int user_id,
                                                     @RequestHeader("token") String token,
                                                     @RequestBody(required = false) Object relatedLocationRequest);
    // Progress Report

    @PostMapping(value = "/progressReport")
    ResponseEntity<Object> saveProgressReport(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object progressReportRequest);

    @GetMapping(value = "/progressReport")
    ResponseEntity<List<Object>> getProgressReport(@RequestHeader(value = "userId") Integer userId,
                                                   @RequestHeader(value = "token") String token);

    @GetMapping(value = "/progressReport/{id}")
    ResponseEntity<Object> getProgressReportById(@RequestHeader(value = "userId") Integer userId,
                                                 @RequestHeader(value = "token") String token,
                                                 @PathVariable(value = "id") long progressReportId);

    // Projects

    @RequestMapping(value = "/rest/v1/projects", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> restProjects(@RequestParam("user_id") int user_id,
                                                     @RequestParam("token") String token,
                                                     @RequestParam(value = "lastSync", required = false) String lastSync) throws JsonParseException, JsonMappingException, IOException;


    @RequestMapping(value = "/project", method = RequestMethod.POST)
    Object addProject(@RequestHeader("userId") int userId,
                      @RequestHeader("token") String token,
                      @RequestBody Object projectCreateRequest);

    @RequestMapping(value = "/project", method = RequestMethod.PUT)
    Object updateProject(@RequestHeader("userId") int userId,
                         @RequestHeader("token") String token,
                         @RequestBody Object projectUpdateRequest);

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    List<Object> getProjects(@RequestHeader("userId") int userId,
                             @RequestHeader("token") String token);

    @RequestMapping(value = "/downloadProjects", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadProjects(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "1000") int pageSize);

    // QC controller
    @RequestMapping(value = "/qc/crfi", method = RequestMethod.POST)
    ResponseEntity<Object> createCRFI(@RequestHeader(value = "userId") int userId,
                                      @RequestHeader(value = "token") String token,
                                      @RequestBody Object activityInspectionRequest);


    @RequestMapping(value = "/qc/crfi", method = RequestMethod.PUT)
    ResponseEntity<Object> updateCRFI(@RequestHeader(value = "userId") int userId,
                                      @RequestHeader(value = "token") String token,
                                      @RequestBody Object activityInspectionUpdateRequest);


    @RequestMapping(value = "/qc/crfi", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getCRFI(@RequestHeader(value = "userId") int userId,
                                         @RequestHeader(value = "token") String token,
                                         @RequestParam(value = "crfiId") long crfiId,
                                         @RequestParam(value = "projectId") long projectId);


    @RequestMapping(value = "/qc/crfiDetails", method = RequestMethod.GET)
    ResponseEntity<Object> getCRFIDetails(@RequestHeader(value = "userId") int userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestParam(value = "crfiId") long crfiId);


    @RequestMapping(value = "/qc/crfiReport", method = RequestMethod.GET)
    ResponseEntity<byte[]> getDataForRFIReport(@RequestParam(value = "user_id") int userId,
                                               @RequestParam(value = "token") String token,
                                               @RequestParam(value = "crfiId", required = true) int crfiId,
                                               @RequestParam(value = "webCall", defaultValue = "false", required = false) boolean webCall);


    @RequestMapping(value = "/qc/filter/crfi", method = RequestMethod.POST)
    ResponseEntity<Object> getObsFilter(@RequestHeader(value = "userId") int userId,
                                        @RequestHeader(value = "token") String token,
                                        @RequestBody Object filterCrfiRequest);

    // QC OBS API

    @RequestMapping(value = "/qc/obs", method = RequestMethod.POST)
    ResponseEntity<Object> createQCObservation(@RequestHeader(value = "userId") Integer userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestBody Object obsRequest);


    @RequestMapping(value = "/qc/obs", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getObs(@RequestHeader(value = "userId") int userId,
                                        @RequestHeader(value = "token") String token,
                                        @RequestParam(value = "obsId") long obsId,
                                        @RequestParam(value = "projectId") long projectId);


    @RequestMapping(value = "/qc/obs", method = RequestMethod.PUT)
    ResponseEntity<Object> updateOBS(@RequestHeader(value = "userId") int userId,
                                     @RequestHeader(value = "token") String token,
                                     @RequestBody Object obsUpdateRequest);


    @RequestMapping(value = "/qc/obsDetails", method = RequestMethod.GET)
    ResponseEntity<Object> getOBSDetails(@RequestHeader(value = "userId") int userId,
                                         @RequestHeader(value = "token") String token,
                                         @RequestParam(value = "obsId") long obsId);

    @RequestMapping(value = "/qc/obsReport", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getDataForQCObsReport(@RequestParam(value = "user_id") int userId,
                                                        @RequestParam(value = "token") String token,
                                                        @RequestParam(value = "obsId", required = true) int obsId,
                                                        @RequestParam(value = "webCall", defaultValue = "false", required = false) boolean webCall);

    @RequestMapping(value = "/qcStages", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAll();

    @RequestMapping(value = "/qcStages", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(@RequestParam String stageName);

    // Report controller

    @RequestMapping(value = "/rorReport", method = RequestMethod.GET)
    void rorReport();

    @RequestMapping(value = "/ptwReport", method = RequestMethod.POST)
    void ptwReport(@RequestBody Object ptwReportRequest);

    @RequestMapping(value = "/ptwReportDownload", method = RequestMethod.GET)
    ResponseEntity<byte[]> ptwReportDownload(
            @RequestParam(value = "user_id") int userId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "ptwId", required = true) int ptwId,
            @RequestParam(value = "webCall", defaultValue = "true", required = false) boolean webCall);

    @RequestMapping(value = "/equipmentReportDownload", method = RequestMethod.GET)
    ResponseEntity<byte[]> equipmentReportDownload(
            @RequestParam(value = "user_id") int userId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "equipmentId") int equipmentId,
            @RequestParam(value = "webCall", defaultValue = "true", required = false) boolean webCall);

    @RequestMapping(value = "/safetyObsReport", method = RequestMethod.GET)
    void safetyObsReport();

    @RequestMapping(value = "/escalateOBSReport", method = RequestMethod.GET)
    void escalateOBSReport();

    @RequestMapping(value = "/safetyObsReportByFilter", method = RequestMethod.GET)
    ResponseEntity<Object> safetyObsReportByFilter(@RequestParam(value = "fromDate") String fromDate,
                                                   @RequestParam(value = "toDate") String toDate,
                                                   @RequestParam(value = "userId") Integer userId,
                                                   @RequestParam(value = "projectId", required = false, defaultValue = "0") Integer projectId);

    @GetMapping("/downloadMemoPdf")
    ResponseEntity<Object> downloadMemoPdf(@RequestHeader("user_id") int user_id,
                                           @RequestHeader("token") String token,
                                           @RequestParam(name = "obsId", required = false, defaultValue = "0") int obsId);


    @GetMapping("/downloadDebitPdf")
    ResponseEntity<Object> downloadDebitPdf(@RequestHeader("user_id") int user_id,
                                            @RequestHeader("token") String token,
                                            @RequestParam(name = "debitId", required = false, defaultValue = "0") int debitId);


    @GetMapping("/downloadIncidentPdf")
    ResponseEntity<Object> downloadIncidentPdf(@RequestHeader("user_id") int user_id,
                                               @RequestHeader("token") String token,
                                               @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId);

    @RequestMapping(value = "/downloadSnagingPdf", method = RequestMethod.POST)
    public ResponseEntity<Object> downloadSnagingPdf(@RequestHeader("user_id") int user_id,
                                                     @RequestHeader("token") String token,
                                                     @RequestBody Object filterRequest);

    @GetMapping("/sendIncidentPdf")
    ResponseEntity<byte[]> sendIncidentPdf(@RequestHeader("user_id") int user_id,
                                           @RequestHeader("token") String token,
                                           @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId);


    @GetMapping("/downloadTBTPdf")
    ResponseEntity<byte[]> downloadTBTPdf(@RequestHeader("user_id") int user_id,
                                          @RequestHeader("token") String token,
                                          @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId);


    @RequestMapping(value = "/equipmentReport", method = RequestMethod.POST)
    ResponseEntity<Object> equipmentReport(@RequestBody Object equipmentReportRequest);


    @RequestMapping(value = "/obsreport", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getObsReport(@RequestParam(value = "user_id") int userId,
                                                     @RequestParam(value = "token") String token,
                                                     @RequestParam(value = "obsId", required = false) int obsId);


    @RequestMapping(value = "/weeklyStatusReport", method = RequestMethod.GET)
    void weeklyStatusReport(@RequestParam(value = "projectId") int projectId,
                            @RequestParam(value = "fromDate") String fromDate,
                            @RequestParam(value = "toDate") String toDate,
                            @RequestParam(value = "locationBased") int locationBased);


    @GetMapping("/downloadWorkerPdf")
    ResponseEntity<byte[]> downloadWorkerPdf(@RequestParam("user_id") int user_id,
                                             @RequestParam("token") String token, @RequestParam(name = "workerId", required = true, defaultValue = "0") int workerId,
                                             @RequestParam(value = "webCall", defaultValue = "true", required = false) boolean webCall);

// Incident controller

    @RequestMapping(value = "/safety/incident", method = RequestMethod.POST)
    ResponseEntity<Object> saveIncident(@RequestHeader(value = "userId") Integer userId,
                                        @RequestHeader(value = "token") String token,
                                        @RequestBody Object request);

    @RequestMapping(value = "/safety/incident", method = RequestMethod.GET)
    ResponseEntity<Object> findSafetyIncident(@RequestHeader(value = "userId") Integer userId,
                                              @RequestHeader(value = "token") String token,
                                              @RequestParam(value = "page_num", defaultValue = "1", required = false) Integer page,
                                              @RequestParam(value = "page_size", defaultValue = "500", required = false) Integer pageSize,
                                              @RequestParam(value = "project_id") Integer projectId);

    @RequestMapping(value = "/safety/incident/{incidentId}", method = RequestMethod.GET)
    ResponseEntity<Object> findIncidentById(@RequestHeader(value = "userId") Integer userId,
                                            @RequestHeader(value = "token") String token,
                                            @PathVariable(value = "incidentId") Integer incidentId);

    // Safety worker controller

    @PostMapping(value = "/SafetyWorkers")
    ResponseEntity<Object> saveSafetyWorkers(@RequestHeader(value = "user_id") int userId,
                                             @RequestHeader(value = "token") String token,
                                             @RequestBody Object safetyWorkersRequest);

    @GetMapping(value = "/SafetyWorkers")
    ResponseEntity<List<Object>> getSafetyWorkers(@RequestHeader(value = "user_id") int userId,
                                                  @RequestHeader(value = "token") String token,
                                                  @RequestParam(value = "project_id") long projectId);

    @GetMapping(value = "/SafetyWorkers/{id}")
    ResponseEntity<Object> getSafetyWorkersById(@RequestHeader(value = "user_id") int userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestParam(value = "id") long workerId);

    @PutMapping(value = "/updateSafetyWorker/{id}")
    ResponseEntity<Object> updateSafetyWorkerById(@RequestHeader(value = "user_id") int userId,
                                                  @RequestHeader(value = "token") String token,
                                                  @RequestParam(value = "id") long workerId,
                                                  @RequestBody Object request);

    // Unit Master

    @GetMapping(value = "/unitMaster")
    ResponseEntity<List<Object>> getAllUnitMaster(@RequestHeader(value = "userId") Integer userId,
                                                  @RequestHeader(value = "token") String token);

    @GetMapping(value = "/unitMaster/{id}")
    ResponseEntity<Object> getUnitMasterById(@RequestHeader(value = "userId") Integer userId,
                                             @RequestHeader(value = "token") String token,
                                             @PathVariable(value = "id") long unitMasterId);


    @RequestMapping(value = "/snag/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createSnag(@RequestHeader(value = "userId") Integer userId,
                                             @RequestHeader(value = "token") String token, @RequestBody Object snagRequest);

    @RequestMapping(value = "/snag/find", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getSnag(@RequestHeader(value = "userId") int userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestParam(value = "snagId") long snagId,
                                                @RequestParam(value = "projectId") long projectId);

    @RequestMapping(value = "/snag/filter", method = RequestMethod.POST)
    public ResponseEntity<Object> getSnagFilter(@RequestHeader(value = "userId") int userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestBody Object filterObsRequest);

    @RequestMapping(value = "/snag/update", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateSnag(@RequestHeader(value = "userId") int userId,
                                             @RequestHeader(value = "token") String token,
                                             @RequestBody Object obsUpdateRequest);

    @RequestMapping(value = "/snag/details", method = RequestMethod.GET)
    public ResponseEntity<Object> getSnagDetails(@RequestHeader(value = "userId") int userId,
                                                 @RequestHeader(value = "token") String token,
                                                 @RequestParam(value = "snagId") long obsId);

    @RequestMapping(value = "/snag/report", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getDataForSnagReport(@RequestParam(value = "user_id") int userId,
                                                       @RequestParam(value = "token") String token,
                                                       @RequestParam(value = "snagId", required = true) int snagId,
                                                       @RequestParam(value = "webCall", defaultValue = "false", required = false) boolean webCall);

    // User controller
    @RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Object> addUser(@RequestHeader(value = "userId") Integer userId,
                                   @RequestHeader(value = "token") String token,
                                   @RequestBody Object userRequestModel);

    @RequestMapping(value = "/user/update", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Object> UpdateUser(@RequestHeader(value = "userId") Integer userId,
                                      @RequestHeader(value = "token") String token,
                                      @RequestBody Object userRequestModel);

    @RequestMapping(value = "/user/fetch", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<List<Object>> getUsers(@RequestHeader(value = "userId") Integer userId,
                                          @RequestHeader(value = "token") String token,
                                          @RequestParam(value = "projectId", required = false, defaultValue = "0") int projectId,
                                          @RequestParam(value = "companyId", required = false, defaultValue = "0") int companyId,
                                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "1000") int pageSize);

    @PostMapping(value = "/user/filter")
    public ResponseEntity<Object> filterUsers(
            @RequestHeader(value = "userId") Integer userId,
            @RequestHeader(value = "token") String token,
            @RequestBody Object filterRequest);

    @GetMapping(value = "/user/getNonProjectUsers")
    ResponseEntity<List<Object>> getNonProjectUsers(@RequestParam(value = "projectId") int projectId,
                                                    @RequestParam(value = "companyId") int companyId);

    @RequestMapping(value = "/user/userMapping", method = RequestMethod.POST)
    ResponseEntity<Object> userMapping(@RequestBody Object userMappingRequest);

    @RequestMapping(value = "/user/deleteUser", method = RequestMethod.DELETE, produces = {"application/json"})
    ResponseEntity<Object> deleteUser(@RequestParam(value = "user_id") Integer userId);

    @RequestMapping(value = "/user/updateUserStatus", method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<Object> updateUserStatus(@RequestParam(value = "user_id") Integer userId,
                                                   @RequestParam(value = "active") int active);

    @RequestMapping(value = "/user/downloadUsers", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadUsersExcel(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "projectId", required = false, defaultValue = "0") int projectId,
            @RequestParam(value = "companyId", required = false, defaultValue = "0") int companyId,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "1000") int pageSize);

    @RequestMapping(value = "/addTypeOfWork", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addTypeOfWork(
            @RequestParam(value = "label") String label,
            @RequestParam(value = "type") String type);

    @RequestMapping(value = "/updateTypeOfWork", method = RequestMethod.POST)
    @ResponseBody // Make sure this is present if you aren't using @RestController
    public Map<String, Object> updateTypeOfWork(
            @RequestParam(value = "label") String label,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "id") int id);

    @RequestMapping(value = "/addEQPTWChecklist", consumes = "multipart/form-data", method = RequestMethod.POST)
    @ResponseBody // Make sure this is present if you aren't using @RestController
    public Map<String, Object> addEQPTWChecklist(@RequestParam(value = "userId") int userId,
                                                 @RequestPart(value = "file") MultipartFile file);

    @PostMapping("/rest/v1/users/userinfo")
    public ResponseEntity<Object> addDeviceTokenAndAppVersion(@RequestHeader("user_id") int userId,
                                                              @RequestHeader("token") String token,
                                                              @RequestParam("deviceToken") String deviceToken,
                                                              @RequestParam(value = "appVersion", defaultValue = "1", required = false) String appVersion);

    @RequestMapping(value = "/addHazard", method = RequestMethod.POST)
    public Map<String, Object> addHazard(@RequestParam(value = "label") String label);

    @RequestMapping(value = "/addEquipment", method = RequestMethod.POST)
    public Map<String, Object> addEQ(@RequestParam(value = "label") String label);

    @RequestMapping(value = "/updateHazard", method = RequestMethod.POST)
    @ResponseBody // Make sure this is present if you aren't using @RestController
    public Map<String, Object> updateHazard(
            @RequestParam(value = "label") String label,
            @RequestParam(value = "id") int id);

    @RequestMapping(value = "/updateEquipment", method = RequestMethod.POST)
    @ResponseBody // Make sure this is present if you aren't using @RestController
    public Map<String, Object> updateEQ(
            @RequestParam(value = "label") String label,
            @RequestParam(value = "id") int id);

    @RequestMapping(value = "/getHazards", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getHazards();

    @RequestMapping(value = "/getEquipments", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getEquipments();

    @RequestMapping(value = "/uploadEquipmentChecklist", consumes = "multipart/form-data", method = RequestMethod.POST)
    @ResponseBody // Make sure this is present if you aren't using @RestController
    public Map<String, Object> uploadEquipmentChecklist(@RequestParam(value = "userId") int userId,
                                                        @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/uploadHazardChecklist", consumes = "multipart/form-data", method = RequestMethod.POST)
    @ResponseBody // Make sure this is present if you aren't using @RestController
    public Map<String, Object> uploadHazardChecklist(@RequestParam(value = "userId") int userId,
                                                     @RequestPart(value = "file") MultipartFile file);

}
