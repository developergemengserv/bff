
package com.gemengserv.bff.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gemengserv.bff.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "ashwinshethadmin", url = "http://localhost:8089", configuration = FeignConfig.class)
public interface AshwinShethAdminService {

    @RequestMapping(value = "/rest/api/v1/activity/master/findAll", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllActivities(
            @RequestParam("user_id") int userId,
            @RequestParam("token") String token,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum
    );

    @RequestMapping(value = "/rest/api/v1/activity/master/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getMasterActivityById(
            @RequestParam("user_id") int userId,
            @RequestParam("token") String token,
            @RequestParam(value = "activity_id") int activityId);

    @RequestMapping(value = "/rest/api/v1/activity/master/create", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> createActivityMasterData(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/activity/master/update", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> updateActivityMasterData(@RequestBody Map<String, Object> paramObj);

    //Common Controller
    @RequestMapping(value = "/rest/api/v1/common/login", method = GET)
    ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username,
                                              @RequestParam("password") String password);

    @RequestMapping(value = "/rest/api/v1/common/checkotp", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> checkotp(@RequestParam("otp") int otp,
                                                 @RequestParam("user_id") int user_id,
                                                 @RequestParam("version") String version);

    @RequestMapping(value = "/rest/api/v1/common/logout", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> logout(@RequestParam("user_id") int user_id,
                                               @RequestParam("version") String version);

    @RequestMapping(value = "/rest/v1/common/getUnitMaster", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getUnitMaster(@RequestParam("user_id") int userId, @RequestParam("token") String token);

    @RequestMapping(value = "rest/api/v1/common/getStatus", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getStatus(@RequestParam("user_id") int user_id, @RequestParam("token") String token, @RequestParam("eventType") String eventType);

    @RequestMapping(value = "rest/api/v1/common/getObservationType", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getObservationType(@RequestParam("user_id") int user_id, @RequestParam("token") String token);

    @RequestMapping(value = "rest/api/v1/common/getTopics", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getTopics(@RequestParam("user_id") int user_id, @RequestParam("token") String token);

    @RequestMapping(value = "/getYears", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getYears();

    @RequestMapping(value = "/rest/api/v1/mapping/company/activity/create", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> addCompanyActivityMapping(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/mapping/company/activity/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getCompanyActivityMapping(@RequestParam(value = "user_id") int userId,
                                                                  @RequestParam(value = "token") String token,
                                                                  @RequestParam(value = "project_id") int projectId,
                                                                  @RequestParam(value = "company_id") int companyId);

    @RequestMapping(value = "/rest/api/v1/mapping/company/activity/update", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> updateCompanyActivityMapping(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/company/create", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> addCompany(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/company/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getCompanyDetail(@RequestParam(value = "user_id") int userId,
                                                         @RequestParam(value = "token") String token,
                                                         @RequestParam(value = "company_id") int companyId);

    @RequestMapping(value = "/rest/api/v1/company/update", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> updateCompanyDetail(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/company/findAll", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getCompanyDetail(@RequestParam(value = "user_id") int userId,
                                                         @RequestParam(value = "token") String token);

    @RequestMapping(value = "/rest/api/v1/company/media/upload/projectLogo", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> uploadSignature(@RequestParam("user_id") int user_id,
                                                        @RequestParam("token") String token,
                                                        @RequestParam("company_id") int companyId,
                                                        @RequestParam(value = "file") MultipartFile file);

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getQCChartInfo", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getQCChartInfo(@RequestParam("user_id") int user_id,
                                                       @RequestParam("token") String token, @RequestParam("project_id") String project_id, @RequestParam("periodicalType") String periodicalType) throws JsonParseException, JsonMappingException, IOException;

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getQCRejectionChartInfo", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getQCRejectionChartInfo(@RequestParam("user_id") int user_id,
                                                                @RequestParam("token") String token, @RequestParam("project_id") String project_id, @RequestParam("periodicalType") String periodicalType, @RequestParam("activityIds") String activityIds) throws JsonParseException, JsonMappingException, IOException;

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getQCTATChartInfo", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getQCTATChartInfo(@RequestParam("user_id") int user_id,
                                                          @RequestParam("token") String token, @RequestParam("project_id") String project_id, @RequestParam("periodicalType") String periodicalType, @RequestParam("activityIds") String activityIds) throws JsonParseException, JsonMappingException, IOException;

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getLocationForProject", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getLocationForProject(@RequestParam("user_id") int user_id,
                                                              @RequestParam("token") String token, @RequestParam("project_id") String project_id) throws IOException;

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getQCIssueChartInfo", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getQCIssueChartInfo(@RequestParam("user_id") int user_id,
                                                            @RequestParam("token") String token, @RequestParam("project_id") String project_id, @RequestParam("periodicalType") String periodicalType, @RequestParam("location") String location, @RequestParam("issue") String issue, @RequestParam("status") String status) throws IOException;

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getActivitiesList", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getActivities(@RequestParam("user_id") int user_id,
                                                      @RequestParam("token") String token, @RequestParam("project_id") String project_id) throws IOException;

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getSafetyDashboardCounts", method = RequestMethod.GET)
    ResponseEntity<Object> getSafetyDashboardCounts(@RequestHeader("user_id") int user_id,
                                                    @RequestHeader("token") String token,
                                                    @RequestParam("project_id") int projectId);

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getDateRangeReportDashboard", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getDateRangeReportDashboard(@RequestHeader(value = "user_id") int user_id,
                                                                    @RequestHeader(value = "token") String token,
                                                                    @RequestParam(value = "project_id") int project_id,
                                                                    @RequestParam(value = "fromDate") String fromDate,
                                                                    @RequestParam(value = "toDate") String toDate);

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getDateRangeReportExcel", method = RequestMethod.GET)
    ResponseEntity<Resource> getDateRangeReportExcel(@RequestHeader(value = "user_id") int user_id,
                                                     @RequestHeader(value = "token") String token,
                                                     @RequestParam(value = "project_id") int project_id,
                                                     @RequestParam(value = "fromDate") String fromDate,
                                                     @RequestParam(value = "toDate") String toDate);

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getContractorwiseCounts", method = RequestMethod.GET)
    ResponseEntity<Object> getContractorwiseCounts(@RequestHeader("user_id") int user_id,
                                                   @RequestHeader("token") String token,
                                                   @RequestParam("project_id") int projectId,
                                                   @RequestParam(value = "fromDate") String fromDate,
                                                   @RequestParam(value = "toDate") String toDate);

    @RequestMapping(value = "/getDateRangeAddReportDashboard", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getDateRangeAddReportDashboard(@RequestHeader(value = "user_id") int user_id,
                                                                              @RequestHeader(value = "token") String token,
                                                                              @RequestParam(value = "project_id") int project_id,
                                                                              @RequestParam(value = "term") String term,
                                                                              @RequestParam(value = "period") int period);

    @RequestMapping(value = "/getDashboardReportData", method = RequestMethod.GET)
    public ResponseEntity<Object> getDashboardReportData(@RequestHeader(value = "user_id") int user_id,
                                                         @RequestHeader(value = "token") String token,
                                                         @RequestParam(value = "type") String type,
                                                         @RequestParam(value = "project_id") int project_id,
                                                         @RequestParam(value = "term") String term,
                                                         @RequestParam(value = "period") int period);

    @PostMapping(value = "/hazards/upload/{projectId}", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadHazards(@RequestParam(value = "user_id") Integer userId,
                                         @RequestParam(value = "token") String token,
                                         @PathVariable(value = "projectId") Integer projectId,
                                         @RequestParam(value = "file") MultipartFile file);

    @GetMapping(value = "/getHazardsByProjectId/{project_id}")
    ResponseEntity<List<Object>> getHazardsByProjectId(@RequestHeader(value = "user_id") Integer userId,
                                                       @RequestHeader(value = "token") String token,
                                                       @PathVariable(value = "project_id") int projectId);

    @RequestMapping(value = "/rest/api/v1/location/db/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllLocationsFromDB(
            @RequestParam(value = "project_id", required = true) int pid,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            HttpServletRequest request, @RequestParam("user_id") int user_id, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/api/v1/location/db/findall", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllLocationMaster(
            @RequestParam(value = "project_id", required = true) int project_id,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam("user_id") int user_id, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/api/v1/getLocationByLevel", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getLocationByLevel(@RequestParam(value = "project_id") int project_id,
                                                           @RequestParam(value = "level") int level,
                                                           @RequestParam("user_id") int user_id, @RequestParam("token") String token);

    @RequestMapping(value = "/rest/api/v1/getRelatedLocation", method = RequestMethod.POST)
    ResponseEntity<Object> getRelatedLocation(@RequestHeader("user_id") int user_id,
                                              @RequestHeader("token") String token,
                                              @RequestBody(required = false) Object relatedLocationRequest);

    //MaterialMasterController
    @RequestMapping(value = "/rest/api/v1/material/master/create", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> createMaterialMasterData(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/material/master/update", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> updateMaterialMasterData(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/material/master/findAll", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllMaterialMaster(
            @RequestParam("user_id") int userId,
            @RequestParam("token") String token,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum
    );

    @RequestMapping(value = "/rest/api/v1/material/master/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getMaterialMasterActivityById(
            @RequestParam("user_id") int userId,
            @RequestParam("token") String token,
            @RequestParam(value = "material_id") int materialId);

    //ProjectControlller
    @RequestMapping(value = "/rest/api/v1/project/findAll", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> restProjects(@RequestParam("user_id") int user_id,
                                                     @RequestParam("token") String token) throws IOException;

    @RequestMapping(value = "/rest/api/v1/project/create", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> addProject(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/project/update", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> updateProject(@RequestBody Map<String, Object> paramObj);


    @RequestMapping(value = "/rest/api/v1/project/user/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAssignedProjectRelatedToUser(
            @RequestParam("user_id") int user_id,
            @RequestParam("mapped_user_id") int mappedUserId,
            @RequestParam("token") String token);

    @RequestMapping(value = "/rest/api/v1/project/media/upload/projectLogo", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> uploadProjectLogo(@RequestParam("user_id") int user_id,
                                                          @RequestParam("token") String token,
                                                          @RequestParam("project_id") int project_id,
                                                          @RequestParam(value = "file") MultipartFile file);

    @RequestMapping(value = "/rest/api/v1/project/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> findProjects(@RequestParam("user_id") int userId,
                                                     @RequestParam("token") String token,
                                                     @RequestParam("project_id") int projectId);

    @RequestMapping(value = "/rest/api/v1/project/find/projectsByUserId", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getProjectsByUserId(@RequestParam("user_id") int userId,
                                                            @RequestParam("token") String token);

    @RequestMapping(value = "/rest/api/v1/project/member/create", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> addProjectMember(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/project/member/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getProjectMembers(@RequestParam(name = "user_id") int userId,
                                                          @RequestParam(name = "token") String token,
                                                          @RequestParam(name = "project_id") int projectId);

    @RequestMapping(value = "/rest/api/v1/project/member/update", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> updateProjectMember(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/crfi", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getAllActivityInspectionDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                                                @RequestHeader(value = "token") String token,
                                                                                @RequestBody Object request);

    @RequestMapping(value = "/rest/api/v1/ui/report/crfi", method = RequestMethod.GET)
    ResponseEntity<Resource> getCRFIReport(@RequestParam(value = "user_id") int userId,
                                           @RequestParam(value = "token") String token, @RequestParam(value = "project_id") int projectId,
                                           @RequestParam(required = false) Integer contractorId, @RequestParam(required = false) Integer activityId,
                                           @RequestParam(required = false) Integer statusCode, @RequestParam(required = false) String createdDate);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/obs", method = RequestMethod.GET)
    ResponseEntity<Object> getAllObsDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestParam(value = "project_id") int projectId,
                                                    @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                                    @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                    @RequestParam(value = "checkDebitNote", defaultValue = "0", required = false) int checkDebitNote);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/mrfi", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllMRFIDataForUIReport(@RequestParam(value = "user_id") int userId,
                                                                  @RequestParam(value = "token") String token,
                                                                  @RequestParam(value = "project_id") int projectId,
                                                                  @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                                                  @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/mrfi", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getMRFIWithFilter(@RequestHeader(value = "user_id") int userId,
                                                          @RequestHeader(value = "token") String token,
                                                          @RequestBody Object findMRFIRequest);


    @RequestMapping(value = "/rest/api/v1/ui/report/find/ncr", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllNCRDataForUIReport(@RequestParam(value = "user_id") int userId,
                                                                 @RequestParam(value = "token") String token,
                                                                 @RequestParam(value = "project_id") int projectId,
                                                                 @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                                                 @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/obs", method = RequestMethod.POST)
    ResponseEntity<Object> getAllObservationDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                            @RequestHeader(value = "token") String token,
                                                            @RequestBody Object findObsRequest);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/obs/pdf", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getAllObservationDataForPDFReport(@RequestParam(value = "user_id") int userId,
                                                                          @RequestParam(value = "token") String token,
                                                                          @RequestParam(value = "project_id") int projectId,
                                                                          @RequestParam(value = "fromDate", required = false) String fromDate,
                                                                          @RequestParam(value = "toDate", required = false) String toDate);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/tbt", method = RequestMethod.GET)
    ResponseEntity<Object> getAllTbtDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestParam(value = "project_id") int projectId,
                                                    @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                                    @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/tbt", method = RequestMethod.POST)
    ResponseEntity<Object> getTbtFilterDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                       @RequestHeader(value = "token") String token,
                                                       @RequestBody Object findTbtRequest);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/meeting", method = RequestMethod.POST)
    ResponseEntity<Object> getMeetingFilterDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                           @RequestHeader(value = "token") String token,
                                                           @RequestBody Object findMeetingRequest);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/ec", method = RequestMethod.POST)
    ResponseEntity<Object> getECDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestBody Object findECRequest);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/incident", method = RequestMethod.POST)
    ResponseEntity<Object> getIncidentDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                      @RequestHeader(value = "token") String token,
                                                      @RequestBody Object incidentRequest);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/ptw", method = RequestMethod.POST)
    ResponseEntity<Object> getPTWDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                 @RequestHeader(value = "token") String token,
                                                 @RequestBody Object ptwRequest);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/GoodPractices", method = RequestMethod.POST)
    ResponseEntity<Object> getGPDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                @RequestHeader(value = "token") String token,
                                                @RequestBody Object gpRequest);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/Workers", method = RequestMethod.POST)
    ResponseEntity<Object> getWorkerDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                    @RequestHeader(value = "token") String token,
                                                    @RequestBody Object workerRequest);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/firstaid", method = RequestMethod.POST)
    ResponseEntity<Object> getFirstaidDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                      @RequestHeader(value = "token") String token,
                                                      @RequestBody Object firstAIDCaseRequest);

    @RequestMapping(value = "/rest/api/v1/ui/report/find/nearmiss", method = RequestMethod.POST)
    ResponseEntity<Object> getNearmissDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                      @RequestHeader(value = "token") String token,
                                                      @RequestBody Object findRequest);

    @GetMapping("/rest/api/v1/ui/report/downloadTBTPdf")
    ResponseEntity<Object> downloadTBTPdf(@RequestHeader("user_id") int user_id,
                                          @RequestHeader("token") String token,
                                          @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId);

    @GetMapping("/rest/api/v1/ui/report/downloadIncidentPdf")
    ResponseEntity<Object> downloadIncidentPdf(@RequestHeader("user_id") int user_id,
                                               @RequestHeader("token") String token,
                                               @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId);

    @RequestMapping(value = "/rest/api/v1/ui/report/downloadEquipmentPdf", method = RequestMethod.GET)
    ResponseEntity<Object> equipmentReport(@RequestHeader("user_id") int user_id,
                                           @RequestHeader("token") String token,
                                           @RequestParam(name = "equipmentId", required = true, defaultValue = "0") int equipmentId);


    @RequestMapping(value = "/rest/api/v1/ui/report/downloadPtwPdf", method = RequestMethod.GET)
    ResponseEntity<Object> ptwReport(@RequestHeader("user_id") int user_id,
                                     @RequestHeader("token") String token,
                                     @RequestParam(name = "ptwId", required = true, defaultValue = "0") int ptwId);

    @RequestMapping(value = "/rest/api/v1/ui/report/downloadObsReport", method = RequestMethod.GET)
    ResponseEntity<Object> getObsReport(@RequestHeader(value = "user_id") int userId,
                                        @RequestHeader(value = "token") String token,
                                        @RequestParam(value = "obsId", required = true, defaultValue = "0") int obsId);

    @RequestMapping(value = "/rest/api/v1/ui/report/meetingReportPdf", method = RequestMethod.GET)
    ResponseEntity<Object> meetingReport(@RequestHeader("user_id") int user_id,
                                         @RequestHeader("token") String token,
                                         @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId);

    @RequestMapping(value = "/rest/api/v1/ui/report/equipment/find", method = RequestMethod.PUT, produces = {"application/json"})
    ResponseEntity<Object> findEquipment(@RequestParam(value = "userId") int userId,
                                         @RequestParam(value = "token") String token);

    @GetMapping("/rest/api/v1/ui/report/downloadGPPdf")
    ResponseEntity<Object> downloadGPPdf(@RequestHeader("user_id") int user_id,
                                         @RequestHeader("token") String token, @RequestParam(name = "goodPracticeId", required = true, defaultValue = "0") int goodPracticeId);

    @GetMapping(value = "/rest/api/v1/ui/report/downloadAllTBTPdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<Object> downloadAllTBTPdf(@RequestHeader("user_id") int user_id,
                                             @RequestHeader("token") String token, @RequestParam(name = "tbtId", required = true, defaultValue = "0") int[] tbtIds);


    @RequestMapping(value = "/rest/api/v1/ui/report/getHSEDashboardReportData", method = RequestMethod.POST)
    ResponseEntity<Object> getHSEDashboardReportData(@RequestHeader(value = "user_id") int userId,
                                                     @RequestHeader(value = "token") String token,
                                                     @RequestBody Object hseDashboardRequest);

    @RequestMapping(value = "/rest/api/v1/ui/report/getHSECountReportData", method = RequestMethod.POST)
    ResponseEntity<Object> getHSECountReportData(@RequestBody Object hseCountReportRequest);


    @RequestMapping(value = "/rest/api/v1/inactiveEquipment", method = RequestMethod.PUT, produces = {"application/json"})
    ResponseEntity<Object> inactiveEquipmentRecords(@RequestParam(value = "userId") int userId,
                                                    @RequestParam(value = "equipmentId") List<Integer> equipmentIds);

    @RequestMapping(value = "/rest/api/v1/inactivePTW", method = RequestMethod.PUT, produces = {"application/json"})
    ResponseEntity<Object> inactivePTWRecords(@RequestParam(value = "userId") int userId,
                                              @RequestParam(value = "ptwIds") List<Integer> ptwIds);

    @RequestMapping(value = "/rest/api/v1/inactiveIncident", method = RequestMethod.PUT, produces = {"application/json"})
    ResponseEntity<Object> inactiveIncidentRecords(@RequestParam(value = "userId") int userId,
                                                   @RequestParam(value = "incidentIds") List<Integer> incidentIds);

    @RequestMapping(value = "/rest/api/v1/inactiveTbt", method = RequestMethod.PUT, produces = {"application/json"})
    ResponseEntity<Object> inactiveTbtRecords(@RequestParam(value = "userId") int userId,
                                              @RequestParam(value = "tbtIds") List<Integer> tbtIds);

    @RequestMapping(value = "/rest/api/v1/inactiveOBS", method = RequestMethod.PUT, produces = {"application/json"})
    ResponseEntity<Object> inactiveOBSRecords(@RequestParam(value = "userId") int userId,
                                              @RequestParam(value = "obsIds") List<Integer> obsIds);

    @RequestMapping(value = "/rest/api/v1/inactiveGP", method = RequestMethod.PUT, produces = {"application/json"})
    ResponseEntity<Object> inactiveGPRecords(@RequestParam(value = "userId") int userId,
                                             @RequestParam(value = "gpIds") List<Integer> gpIds);

    @RequestMapping(value = "/rest/api/v1/mapping/user/activity/create", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> addUserActivityMapping(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/mapping/user/activity/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> findUserActivityMapping(@RequestParam(value = "user_id") int userId,
                                                                @RequestParam(value = "token") String token,
                                                                @RequestParam(value = "project_id") int projectId,
                                                                @RequestParam(value = "mapped_user_id") int mappedUserId);

    @RequestMapping(value = "/rest/api/v1/mapping/user/activity/update", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> updateUserActivityMapping(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/user/findAll", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(value = "user_id") int userId,
                                                    @RequestParam(value = "token") String token,
                                                    @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                                    @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                    @RequestParam(value = "company_id", required = false) List<Integer> companyId);

    @RequestMapping(value = "/rest/api/v1/user/create", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> addUser(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/user/update", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> updateUser(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/user/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getUserByUserId(@RequestParam(value = "user_id") int userId,
                                                        @RequestParam(value = "token") String token,
                                                        @RequestParam(value = "mapped_user_id") int mappedUserId);

    @RequestMapping(value = "/rest/api/v1/user/media/upload/userPicture", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> uploadUserSignature(@RequestParam("user_id") int user_id,
                                                            @RequestParam("token") String token,
                                                            @RequestParam("mapped_user_id") int mappedUserId,
                                                            @RequestParam(value = "file") MultipartFile file);

    @RequestMapping(value = "/rest/api/v1/user/deleteUser", method = RequestMethod.DELETE, produces = {"application/json"})
    ResponseEntity<Object> deleteUser(@RequestParam(value = "user_id") Integer userId);

    @RequestMapping(value = "/rest/api/v1/mapping/user/location/create", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> userLocationMapping(@RequestBody Map<String, Object> paramObj);

    @RequestMapping(value = "/rest/api/v1/mapping/user/location/find", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> getUserLocationMappingData(@RequestParam(name = "user_id") int userId,
                                                                   @RequestParam(name = "token") String token,
                                                                   @RequestParam(name = "project_id") int projectId,
                                                                   @RequestParam(name = "mapped_user_id") int mappedUserId);

    @RequestMapping(value = "/rest/api/v1/mapping/user/location/update", method = RequestMethod.POST, produces = {"application/json"})
    ResponseEntity<Map<String, Object>> updateUserLocationMappingForGivenUser(@RequestBody Map<String, Object> paramObj);

}
