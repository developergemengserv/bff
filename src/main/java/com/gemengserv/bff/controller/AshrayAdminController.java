package com.gemengserv.bff.controller;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gemengserv.bff.service.AshrayAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/ashrayadmin")
public class AshrayAdminController {

    @Autowired
    AshrayAdminService ashrayAdminService;

    @RequestMapping(value = "/rest/api/v1/activity/master/findAll", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllActivities(
            @RequestParam("user_id") int userId,
            @RequestParam("token") String token,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum) {
        return ashrayAdminService.getAllActivities(userId, token, pageSize, pageNum);
    }

    @RequestMapping(value = "/rest/api/v1/activity/master/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getMasterActivityById(
            @RequestParam("user_id") int userId,
            @RequestParam("token") String token,
            @RequestParam(value = "activity_id") int activityId) {
        return ashrayAdminService.getMasterActivityById(userId, token, activityId);
    }

    @RequestMapping(value = "/rest/api/v1/activity/master/create", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> createActivityMasterData(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.createActivityMasterData(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/activity/master/update", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> updateActivityMasterData(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.updateActivityMasterData(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/common/login", method = GET)
    public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username,
                                                     @RequestParam("password") String password) {
        return ashrayAdminService.login(username, password);
    }

    @RequestMapping(value = "/rest/api/v1/common/checkotp", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> checkotp(@RequestParam("otp") int otp,
                                                        @RequestParam("user_id") int user_id,
                                                        @RequestParam("version") String version) {
        return ashrayAdminService.checkotp(otp, user_id, version);
    }

    @RequestMapping(value = "/rest/api/v1/common/logout", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> logout(@RequestParam("user_id") int user_id,
                                                      @RequestParam("version") String version) {
        return ashrayAdminService.logout(user_id, version);
    }

    @RequestMapping(value = "/rest/v1/common/getUnitMaster", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getUnitMaster(@RequestParam("user_id") int userId, @RequestParam("token") String token) {
        return ashrayAdminService.getUnitMaster(userId, token);
    }

    @RequestMapping(value = "rest/api/v1/common/getStatus", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getStatus(@RequestParam("user_id") int user_id, @RequestParam("token") String token, @RequestParam("eventType") String eventType) {
        return ashrayAdminService.getStatus(user_id, token, eventType);
    }

    @RequestMapping(value = "rest/api/v1/common/getObservationType", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getObservationType(@RequestParam("user_id") int user_id, @RequestParam("token") String token) {
        return ashrayAdminService.getObservationType(user_id, token);
    }

    @RequestMapping(value = "rest/api/v1/common/getTopics", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getTopics(@RequestParam("user_id") int user_id, @RequestParam("token") String token) {
        return ashrayAdminService.getTopics(user_id, token);
    }

    @RequestMapping(value = "/getYears", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getYears()
    {
        return ashrayAdminService.getYears();
    }

//CompanyActivityMappingController
    @RequestMapping(value = "/rest/api/v1/mapping/company/activity/create", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> addCompanyActivityMapping(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.addCompanyActivityMapping(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/mapping/company/activity/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getCompanyActivityMapping(@RequestParam(value = "user_id") int userId,
                                                                         @RequestParam(value = "token") String token,
                                                                         @RequestParam(value = "project_id") int projectId,
                                                                         @RequestParam(value = "company_id") int companyId) {
        return ashrayAdminService.getCompanyActivityMapping(userId, token, projectId, companyId);
    }

    @RequestMapping(value = "/rest/api/v1/mapping/company/activity/update", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> updateCompanyActivityMapping(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.updateCompanyActivityMapping(paramObj);
    }

    //CompanyController
    @RequestMapping(value = "/rest/api/v1/company/create", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> addCompany(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.addCompany(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/company/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getCompanyDetail(@RequestParam(value = "user_id") int userId,
                                                                @RequestParam(value = "token") String token,
                                                                @RequestParam(value = "company_id") int companyId) {
        return ashrayAdminService.getCompanyDetail(userId, token, companyId);
    }

    @RequestMapping(value = "/rest/api/v1/company/update", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> updateCompanyDetail(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.updateCompanyDetail(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/company/findAll", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getCompanyDetail(@RequestParam(value = "user_id") int userId,
                                                                @RequestParam(value = "token") String token) {
        return ashrayAdminService.getCompanyDetail(userId, token);
    }

    @RequestMapping(value = "/rest/api/v1/company/media/upload/projectLogo", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadSignature(@RequestParam("user_id") int user_id,
                                                               @RequestParam("token") String token,
                                                               @RequestParam("company_id") int companyId,
                                                               @RequestPart(value = "file") MultipartFile file) {
        return ashrayAdminService.uploadSignature(user_id, token, companyId, file);
    }

    //DashboardChartController
    @RequestMapping(value = "/rest/api/v1/dashboardchart/getQCChartInfo", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getQCChartInfo(@RequestParam("user_id") int user_id,
                                                              @RequestParam("token") String token, @RequestParam("project_id") String project_id, @RequestParam("periodicalType") String periodicalType) throws JsonParseException, JsonMappingException, IOException {
        return ashrayAdminService.getQCChartInfo(user_id, token, project_id, periodicalType);
    }

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getQCRejectionChartInfo", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getQCRejectionChartInfo(@RequestParam("user_id") int user_id,
                                                                       @RequestParam("token") String token, @RequestParam("project_id") String project_id, @RequestParam("periodicalType") String periodicalType, @RequestParam("activityIds") String activityIds) throws JsonParseException, JsonMappingException, IOException {
        return ashrayAdminService.getQCRejectionChartInfo(user_id, token, project_id, periodicalType, activityIds);
    }

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getQCTATChartInfo", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getQCTATChartInfo(@RequestParam("user_id") int user_id,
                                                                 @RequestParam("token") String token, @RequestParam("project_id") String project_id, @RequestParam("periodicalType") String periodicalType, @RequestParam("activityIds") String activityIds) throws JsonParseException, JsonMappingException, IOException {
        return ashrayAdminService.getQCTATChartInfo(user_id, token, project_id, periodicalType, activityIds);
    }

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getLocationForProject", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getLocationForProject(@RequestParam("user_id") int user_id,
                                                                     @RequestParam("token") String token, @RequestParam("project_id") String project_id) throws JsonParseException, JsonMappingException, IOException {
        return ashrayAdminService.getLocationForProject(user_id, token, project_id);
    }

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getQCIssueChartInfo", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getQCIssueChartInfo(@RequestParam("user_id") int user_id,
                                                                   @RequestParam("token") String token, @RequestParam("project_id") String project_id, @RequestParam("periodicalType") String periodicalType, @RequestParam("location") String location, @RequestParam("issue") String issue, @RequestParam("status") String status) throws JsonParseException, JsonMappingException, IOException {
        return ashrayAdminService.getQCIssueChartInfo(user_id, token, project_id, periodicalType, location, issue, status);
    }

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getActivitiesList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getActivities(@RequestParam("user_id") int user_id,
                                                             @RequestParam("token") String token, @RequestParam("project_id") String project_id) throws JsonParseException, JsonMappingException, IOException {
        return ashrayAdminService.getActivities(user_id, token, project_id);
    }

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getSafetyDashboardCounts", method = RequestMethod.GET)
    public ResponseEntity<Object> getSafetyDashboardCounts(@RequestHeader("user_id") int user_id,
                                                           @RequestHeader("token") String token,
                                                           @RequestParam("project_id") int projectId) {
        return ashrayAdminService.getSafetyDashboardCounts(user_id, token, projectId);
    }

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getDateRangeReportDashboard", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getDateRangeReportDashboard(@RequestHeader(value = "user_id") int user_id,
                                                                           @RequestHeader(value = "token") String token,
                                                                           @RequestParam(value = "project_id") int project_id,
                                                                           @RequestParam(value = "fromDate") String fromDate,
                                                                           @RequestParam(value = "toDate") String toDate) {
        return ashrayAdminService.getDateRangeReportDashboard(user_id, token, project_id, fromDate, toDate);
    }

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getDateRangeReportExcel", method = RequestMethod.GET)
    public ResponseEntity<Resource> getDateRangeReportExcel(@RequestHeader(value = "user_id") int user_id,
                                                            @RequestHeader(value = "token") String token,
                                                            @RequestParam(value = "project_id") int project_id,
                                                            @RequestParam(value = "fromDate") String fromDate,
                                                            @RequestParam(value = "toDate") String toDate) {
        return ashrayAdminService.getDateRangeReportExcel(user_id, token, project_id, fromDate, toDate);
    }

    @RequestMapping(value = "/rest/api/v1/dashboardchart/getContractorwiseCounts", method = RequestMethod.GET)
    public ResponseEntity<Object> getContractorwiseCounts(@RequestHeader("user_id") int user_id,
                                                          @RequestHeader("token") String token,
                                                          @RequestParam("project_id") int projectId,
                                                          @RequestParam(value = "fromDate") String fromDate,
                                                          @RequestParam(value = "toDate") String toDate) {
        return ashrayAdminService.getContractorwiseCounts(user_id, token, projectId, fromDate, toDate);
    }

    @RequestMapping(value = "/getDateRangeAddReportDashboard", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getDateRangeAddReportDashboard(@RequestHeader(value = "user_id") int user_id,
                                                                              @RequestHeader(value = "token") String token,
                                                                              @RequestParam(value = "project_id") int project_id,
                                                                              @RequestParam(value = "term") String term,
                                                                              @RequestParam(value = "period") int period)
    {
        return ashrayAdminService.getDateRangeAddReportDashboard(user_id, token, project_id, term, period);
    }

    @RequestMapping(value = "/getDashboardReportData", method = RequestMethod.GET)
    public ResponseEntity<Object> getDashboardReportData(@RequestHeader(value = "user_id") int user_id,
                                                         @RequestHeader(value = "token") String token,
                                                         @RequestParam(value = "type") String type,
                                                         @RequestParam(value = "project_id") int project_id,
                                                         @RequestParam(value = "term") String term,
                                                         @RequestParam(value = "period") int period) {
        return ashrayAdminService.getDashboardReportData(user_id, token, type, project_id, term, period);
    }

    //HazardsController
    @PostMapping(value = "/hazards/upload/{projectId}", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadHazards(@RequestParam(value = "user_id") Integer userId,
                                                @RequestParam(value = "token") String token,
                                                @PathVariable(value = "projectId") Integer projectId,
                                                @RequestPart(value = "file") MultipartFile file) {
        return ashrayAdminService.uploadHazards(userId, token, projectId, file);
    }

    @GetMapping(value = "/getHazardsByProjectId/{project_id}")
    public ResponseEntity<List<Object>> getHazardsByProjectId(@RequestHeader(value = "user_id") Integer userId,
                                                              @RequestHeader(value = "token") String token,
                                                              @PathVariable(value = "project_id") int projectId) {
        return ashrayAdminService.getHazardsByProjectId(userId, token, projectId);
    }

    //LocationMasterController
    @RequestMapping(value = "/rest/api/v1/location/db/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllLocationsFromDB(
            @RequestParam(value = "project_id", required = true) int pid,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
             @RequestParam("user_id") int user_id, @RequestParam("token") String token) {
        return ashrayAdminService.getAllLocationsFromDB(pid, page, pageSize, user_id, token);
    }

    @RequestMapping(value = "/rest/api/v1/location/db/findall", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllLocationMaster(
            @RequestParam(value = "project_id", required = true) int project_id,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam("user_id") int user_id, @RequestParam("token") String token) {
        return ashrayAdminService.getAllLocationMaster(project_id, page, pageSize, user_id, token);
    }

    @RequestMapping(value = "/rest/api/v1/getLocationByLevel", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getLocationByLevel(@RequestParam(value = "project_id") int project_id,
                                                                  @RequestParam(value = "level") int level,
                                                                  @RequestParam("user_id") int user_id, @RequestParam("token") String token) {
        return ashrayAdminService.getLocationByLevel(project_id, level, user_id, token);
    }

    @RequestMapping(value = "/rest/api/v1/getRelatedLocation", method = RequestMethod.POST)
    public ResponseEntity<Object> getRelatedLocation(@RequestHeader("user_id") int user_id,
                                                     @RequestHeader("token") String token,
                                                     @RequestBody(required = false) Object relatedLocationRequest) {
        return ashrayAdminService.getRelatedLocation(user_id, token, relatedLocationRequest);
    }

    //MaterialMasterController
    @RequestMapping(value = "/rest/api/v1/material/master/create", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> createMaterialMasterData(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.createMaterialMasterData(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/material/master/update", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> updateMaterialMasterData(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.updateMaterialMasterData(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/material/master/findAll", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllMaterialMaster(
            @RequestParam("user_id") int userId,
            @RequestParam("token") String token,
            @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum) {
        return ashrayAdminService.getAllMaterialMaster(userId, token, pageSize, pageNum);
    }

    @RequestMapping(value = "/rest/api/v1/material/master/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getMaterialMasterActivityById(
            @RequestParam("user_id") int userId,
            @RequestParam("token") String token,
            @RequestParam(value = "material_id") int materialId) {
        return ashrayAdminService.getMaterialMasterActivityById(userId, token, materialId);
    }

    //ProjectController
    @RequestMapping(value = "/rest/api/v1/project/findAll", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> restProjects(@RequestParam("user_id") int user_id,
                                                            @RequestParam("token") String token) throws JsonParseException, JsonMappingException, IOException {
        return ashrayAdminService.restProjects(user_id, token);
    }

    @RequestMapping(value = "/rest/api/v1/project/create", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> addProject(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.addProject(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/project/update", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> updateProject(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.updateProject(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/project/user/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAssignedProjectRelatedToUser(
            @RequestParam("user_id") int user_id,
            @RequestParam("mapped_user_id") int mappedUserId,
            @RequestParam("token") String token) {
        return ashrayAdminService.getAssignedProjectRelatedToUser(user_id, mappedUserId, token);
    }

    @RequestMapping(value = "/rest/api/v1/project/media/upload/projectLogo", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadProjectLogo(@RequestParam("user_id") int user_id,
                                                                 @RequestParam("token") String token,
                                                                 @RequestParam("project_id") int project_id,
                                                                 @RequestPart(value = "file") MultipartFile file) {
        return ashrayAdminService.uploadProjectLogo(user_id, token, project_id, file);
    }

    @RequestMapping(value = "/rest/api/v1/project/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findProjects(@RequestParam("user_id") int userId,
                                                            @RequestParam("token") String token,
                                                            @RequestParam("project_id") int projectId) {
        return ashrayAdminService.findProjects(userId, token, projectId);
    }

    @RequestMapping(value = "/rest/api/v1/project/find/projectsByUserId", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getProjectsByUserId(@RequestParam("user_id") int userId,
                                                                   @RequestParam("token") String token) {
        return ashrayAdminService.getProjectsByUserId(userId, token);
    }

    //ProjectMemberController
    @RequestMapping(value = "/rest/api/v1/project/member/create", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> addProjectMember(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.addProjectMember(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/project/member/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getProjectMembers(@RequestParam(name = "user_id") int userId,
                                                                 @RequestParam(name = "token") String token,
                                                                 @RequestParam(name = "project_id") int projectId) {
        return ashrayAdminService.getProjectMembers(userId, token, projectId);
    }

    @RequestMapping(value = "/rest/api/v1/project/member/update", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> updateProjectMember(@RequestBody Map<String, Object> paramObj) {
        return ashrayAdminService.updateProjectMember(paramObj);
    }

    //ReportControllerForUI
    @RequestMapping(value = "/rest/api/v1/ui/report/find/crfi", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAllActivityInspectionDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                                                       @RequestHeader(value = "token") String token,
                                                                                       @RequestBody Object request) {
        return ashrayAdminService.getAllActivityInspectionDataForUIReport(userId, token, request);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/crfi", method = RequestMethod.GET)
    public ResponseEntity<Resource> getCRFIReport(@RequestParam(value = "user_id") int userId,
                                                  @RequestParam(value = "token") String token, @RequestParam(value = "project_id") int projectId,
                                                  @RequestParam(required = false) Integer contractorId, @RequestParam(required = false) Integer activityId,
                                                  @RequestParam(required = false) Integer statusCode, @RequestParam(required = false) String createdDate) {
        return ashrayAdminService.getCRFIReport(userId, token, projectId, contractorId, activityId, statusCode, createdDate);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/obs", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllObsDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                           @RequestHeader(value = "token") String token,
                                                           @RequestParam(value = "project_id") int projectId,
                                                           @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                                           @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                           @RequestParam(value = "checkDebitNote", defaultValue = "0", required = false) int checkDebitNote) {
        return ashrayAdminService.getAllObsDataForUIReport(userId, token, projectId, pageNum, pageSize,checkDebitNote);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/mrfi", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllMRFIDataForUIReport(@RequestParam(value = "user_id") int userId,
                                                                         @RequestParam(value = "token") String token,
                                                                         @RequestParam(value = "project_id") int projectId,
                                                                         @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                                                         @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize) {
        return ashrayAdminService.getAllMRFIDataForUIReport(userId, token, projectId, pageNum, pageSize);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/mrfi", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getMRFIWithFilter(@RequestHeader(value = "user_id") int userId,
                                                                 @RequestHeader(value = "token") String token,
                                                                 @RequestBody Object findMRFIRequest) {
        return ashrayAdminService.getMRFIWithFilter(userId, token, findMRFIRequest);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/ncr", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllNCRDataForUIReport(@RequestParam(value = "user_id") int userId,
                                                                        @RequestParam(value = "token") String token,
                                                                        @RequestParam(value = "project_id") int projectId,
                                                                        @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                                                        @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize) {
        return ashrayAdminService.getAllNCRDataForUIReport(userId, token, projectId, pageNum, pageSize);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/obs", method = RequestMethod.POST)
    public ResponseEntity<Object> getAllObservationDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                                   @RequestHeader(value = "token") String token,
                                                                   @RequestBody Object findObsRequest) {
        return ashrayAdminService.getAllObservationDataForUIReport(userId, token, findObsRequest);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/obs/pdf", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllObservationDataForPDFReport(@RequestParam(value = "user_id") int userId,
                                                                                 @RequestParam(value = "token") String token,
                                                                                 @RequestParam(value = "project_id") int projectId,
                                                                                 @RequestParam(value = "fromDate", required = false) String fromDate,
                                                                                 @RequestParam(value = "toDate", required = false) String toDate) {
        return ashrayAdminService.getAllObservationDataForPDFReport(userId, token, projectId, fromDate, toDate);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/tbt", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllTbtDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                           @RequestHeader(value = "token") String token,
                                                           @RequestParam(value = "project_id") int projectId,
                                                           @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                                           @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize) {
        return ashrayAdminService.getAllTbtDataForUIReport(userId, token, projectId, pageNum, pageSize);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/tbt", method = RequestMethod.POST)
    public ResponseEntity<Object> getTbtFilterDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                              @RequestHeader(value = "token") String token,
                                                              @RequestBody Object findTbtRequest) {
        return ashrayAdminService.getTbtFilterDataForUIReport(userId, token, findTbtRequest);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/meeting", method = RequestMethod.POST)
    public ResponseEntity<Object> getMeetingFilterDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                                  @RequestHeader(value = "token") String token,
                                                                  @RequestBody Object findMeetingRequest) {
        return ashrayAdminService.getMeetingFilterDataForUIReport(userId, token, findMeetingRequest);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/ec", method = RequestMethod.POST)
    public ResponseEntity<Object> getECDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                       @RequestHeader(value = "token") String token,
                                                       @RequestBody Object findECRequest) {
        return ashrayAdminService.getECDataForUIReport(userId, token, findECRequest);

    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/incident", method = RequestMethod.POST)
    public ResponseEntity<Object> getIncidentDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                             @RequestHeader(value = "token") String token,
                                                             @RequestBody Object incidentRequest) {
        return ashrayAdminService.getIncidentDataForUIReport(userId, token, incidentRequest);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/ptw", method = RequestMethod.POST)
    public ResponseEntity<Object> getPTWDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                        @RequestHeader(value = "token") String token,
                                                        @RequestBody Object ptwRequest) {
        return ashrayAdminService.getPTWDataForUIReport(userId, token, ptwRequest);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/GoodPractices", method = RequestMethod.POST)
    public ResponseEntity<Object> getGPDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                       @RequestHeader(value = "token") String token,
                                                       @RequestBody Object gpRequest) {
        return ashrayAdminService.getGPDataForUIReport(userId, token, gpRequest);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/Workers", method = RequestMethod.POST)
    public ResponseEntity<Object> getWorkerDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                           @RequestHeader(value = "token") String token,
                                                           @RequestBody Object workerRequest) {
        return ashrayAdminService.getWorkerDataForUIReport(userId, token, workerRequest);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/firstaid", method = RequestMethod.POST)
    public ResponseEntity<Object> getFirstaidDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                             @RequestHeader(value = "token") String token,
                                                             @RequestBody Object firstAIDCaseRequest) {
        return ashrayAdminService.getFirstaidDataForUIReport(userId, token, firstAIDCaseRequest);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/find/nearmiss", method = RequestMethod.POST)
    public ResponseEntity<Object> getNearmissDataForUIReport(@RequestHeader(value = "user_id") int userId,
                                                             @RequestHeader(value = "token") String token,
                                                             @RequestBody Object findRequest) {
        return ashrayAdminService.getNearmissDataForUIReport(userId, token, findRequest);
    }

    @GetMapping("/rest/api/v1/ui/report/downloadTBTPdf")
    public ResponseEntity<Object> downloadTBTPdf(@RequestHeader("user_id") int user_id,
                                                 @RequestHeader("token") String token,
                                                 @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId) {
        return ashrayAdminService.downloadTBTPdf(user_id, token, tbtId);
    }

    @GetMapping("/rest/api/v1/ui/report/downloadIncidentPdf")
    public ResponseEntity<Object> downloadIncidentPdf(@RequestHeader("user_id") int user_id,
                                                      @RequestHeader("token") String token,
                                                      @RequestParam(name = "incidentId", required = false, defaultValue = "0") int incidentId) {
        return ashrayAdminService.downloadIncidentPdf(user_id, token, incidentId);

    }

    @RequestMapping(value = "/rest/api/v1/ui/report/downloadEquipmentPdf", method = RequestMethod.GET)
    public ResponseEntity<Object> equipmentReport(@RequestHeader("user_id") int user_id,
                                                  @RequestHeader("token") String token,
                                                  @RequestParam(name = "equipmentId", required = true, defaultValue = "0") int equipmentId) {
        return ashrayAdminService.equipmentReport(user_id, token, equipmentId);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/downloadPtwPdf", method = RequestMethod.GET)
    public ResponseEntity<Object> ptwReport(@RequestHeader("user_id") int user_id,
                                            @RequestHeader("token") String token,
                                            @RequestParam(name = "ptwId", required = true, defaultValue = "0") int ptwId) {
        return ashrayAdminService.ptwReport(user_id, token, ptwId);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/downloadObsReport", method = RequestMethod.GET)
    public ResponseEntity<Object> getObsReport(@RequestHeader(value = "user_id") int userId,
                                               @RequestHeader(value = "token") String token,
                                               @RequestParam(value = "obsId", required = true, defaultValue = "0") int obsId) {
        return ashrayAdminService.getObsReport(userId, token, obsId);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/meetingReportPdf", method = RequestMethod.GET)
    public ResponseEntity<Object> meetingReport(@RequestHeader("user_id") int user_id,
                                                @RequestHeader("token") String token,
                                                @RequestParam(name = "tbtId", required = true, defaultValue = "0") int tbtId) {
        return ashrayAdminService.meetingReport(user_id, token, tbtId);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/equipment/find", method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<Object> findEquipment(@RequestParam(value = "userId") int userId,
                                                @RequestParam(value = "token") String token) {
        return ashrayAdminService.findEquipment(userId, token);
    }

    @GetMapping("/rest/api/v1/ui/report/downloadGPPdf")
    public ResponseEntity<Object> downloadGPPdf(@RequestHeader("user_id") int user_id,
                                                @RequestHeader("token") String token, @RequestParam(name = "goodPracticeId", required = true, defaultValue = "0") int goodPracticeId) {
        return ashrayAdminService.downloadGPPdf(user_id, token, goodPracticeId);
    }

    @GetMapping(value = "/rest/api/v1/ui/report/downloadAllTBTPdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Object> downloadAllTBTPdf(@RequestHeader("user_id") int user_id,
                                                    @RequestHeader("token") String token, @RequestParam(name = "tbtId", required = true, defaultValue = "0") int[] tbtIds) {
        return ashrayAdminService.downloadAllTBTPdf(user_id, token, tbtIds);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/getHSEDashboardReportData", method = RequestMethod.POST)
    public ResponseEntity<Object> getHSEDashboardReportData(@RequestHeader(value = "user_id") int userId,
                                                            @RequestHeader(value = "token") String token,
                                                            @RequestBody Object hseDashboardRequest) {
        return ashrayAdminService.getHSEDashboardReportData(userId, token, hseDashboardRequest);
    }

    @RequestMapping(value = "/rest/api/v1/ui/report/getHSECountReportData", method = RequestMethod.POST)
    public ResponseEntity<Object> getHSECountReportData(@RequestBody Object hseCountReportRequest) {
        return ashrayAdminService.getHSECountReportData(hseCountReportRequest);
    }

    //SafetyController
    @RequestMapping(value = "/rest/api/v1/inactiveEquipment", method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<Object> inactiveEquipmentRecords(@RequestParam(value = "userId") int userId,
                                                           @RequestParam(value = "equipmentId") List<Integer> equipmentIds)
    {
        return ashrayAdminService.inactiveEquipmentRecords(userId, equipmentIds);
    }

    @RequestMapping(value = "/rest/api/v1/inactivePTW", method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<Object> inactivePTWRecords(@RequestParam(value = "userId") int userId,
                                                     @RequestParam(value = "ptwIds") List<Integer> ptwIds)
    {
        return ashrayAdminService.inactivePTWRecords(userId, ptwIds);
    }

    @RequestMapping(value = "/rest/api/v1/inactiveIncident", method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<Object> inactiveIncidentRecords(@RequestParam(value = "userId") int userId,
                                                          @RequestParam(value = "incidentIds") List<Integer> incidentIds)
    {
        return ashrayAdminService.inactiveIncidentRecords(userId, incidentIds);
    }

    @RequestMapping(value = "/rest/api/v1/inactiveTbt", method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<Object> inactiveTbtRecords(@RequestParam(value = "userId") int userId,
                                                     @RequestParam(value = "tbtIds") List<Integer> tbtIds)
    {
        return ashrayAdminService.inactiveTbtRecords(userId, tbtIds);
    }

    @RequestMapping(value = "/rest/api/v1/inactiveOBS", method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<Object> inactiveOBSRecords(@RequestParam(value = "userId") int userId,
                                                     @RequestParam(value = "obsIds") List<Integer> obsIds) {
        return ashrayAdminService.inactiveOBSRecords(userId, obsIds);
    }

    @RequestMapping(value = "/rest/api/v1/inactiveGP", method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<Object> inactiveGPRecords(@RequestParam(value = "userId") int userId,
                                                    @RequestParam(value = "gpIds") List<Integer> gpIds)
    {
        return ashrayAdminService.inactiveGPRecords(userId, gpIds);
    }

    //UserActivityMappingController
    @RequestMapping(value = "/rest/api/v1/mapping/user/activity/create", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> addUserActivityMapping(@RequestBody Map<String, Object> paramObj)
    {
        return ashrayAdminService.addUserActivityMapping(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/mapping/user/activity/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findUserActivityMapping(@RequestParam(value = "user_id") int userId,
                                                                       @RequestParam(value = "token") String token,
                                                                       @RequestParam(value = "project_id") int projectId,
                                                                       @RequestParam(value = "mapped_user_id") int mappedUserId)
    {
        return ashrayAdminService.findUserActivityMapping(userId, token, projectId, mappedUserId);
    }

    @RequestMapping(value = "/rest/api/v1/mapping/user/activity/update", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> updateUserActivityMapping(@RequestBody Map<String, Object> paramObj)
    {
        return ashrayAdminService.updateUserActivityMapping(paramObj);
    }

    //UserController
    @RequestMapping(value = "/rest/api/v1/user/findAll", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(value = "user_id") int userId,
                                                           @RequestParam(value = "token") String token,
                                                           @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                                                           @RequestParam(value = "page_size", defaultValue = "1000", required = false) int pageSize,
                                                           @RequestParam(value = "company_id", required = false) List<Integer> companyId)
    {
        return ashrayAdminService.getAllUsers(userId, token, pageNum, pageSize, companyId);
    }

    @RequestMapping(value = "/rest/api/v1/user/create", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody Map<String, Object> paramObj)
    {
        return ashrayAdminService.addUser(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/user/update", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody Map<String, Object> paramObj)
    {
        return ashrayAdminService.updateUser(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/user/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getUserByUserId(@RequestParam(value = "user_id") int userId,
                                                               @RequestParam(value = "token") String token,
                                                               @RequestParam(value = "mapped_user_id") int mappedUserId)
    {
        return ashrayAdminService.getUserByUserId(userId, token, mappedUserId);
    }

    @RequestMapping(value = "/rest/api/v1/user/media/upload/userPicture", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadUserSignature(@RequestParam("user_id") int user_id,
                                                                   @RequestParam("token") String token,
                                                                   @RequestParam("mapped_user_id") int mappedUserId,
                                                                   @RequestPart(value = "file") MultipartFile file)
    {
        return ashrayAdminService.uploadUserSignature(user_id, token, mappedUserId, file);
    }

    @RequestMapping(value = "/rest/api/v1/user/deleteUser", method = RequestMethod.DELETE, produces = {"application/json"})
    public ResponseEntity<Object> deleteUser(@RequestParam(value = "user_id") Integer userId)
    {
        return ashrayAdminService.deleteUser(userId);
    }

    //UserLocationMappingController
    @RequestMapping(value = "/rest/api/v1/mapping/user/location/create", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> userLocationMapping(@RequestBody Map<String, Object> paramObj)
    {
        return ashrayAdminService.userLocationMapping(paramObj);
    }

    @RequestMapping(value = "/rest/api/v1/mapping/user/location/find", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getUserLocationMappingData(@RequestParam(name = "user_id") int userId,
                                                                          @RequestParam(name = "token") String token,
                                                                          @RequestParam(name = "project_id") int projectId,
                                                                          @RequestParam(name = "mapped_user_id") int mappedUserId)
    {
        return ashrayAdminService.getUserLocationMappingData(userId, token, projectId, mappedUserId);
    }

    @RequestMapping(value = "/rest/api/v1/mapping/user/location/update", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> updateUserLocationMappingForGivenUser(@RequestBody Map<String, Object> paramObj)
    {
        return ashrayAdminService.updateUserLocationMappingForGivenUser(paramObj);
    }


}
