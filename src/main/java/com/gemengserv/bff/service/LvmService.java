package com.gemengserv.bff.service;

import com.gemengserv.bff.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@FeignClient(name = "lvm", url = "http://localhost:8099", configuration = FeignConfig.class)
public interface LvmService
{
    // Attendance Controller

    @RequestMapping(value = "/attendanceSignIn", method = RequestMethod.POST)
    ResponseEntity<Object> attendanceSignIn(@RequestBody Object attendanceRequest);

    @RequestMapping(value = "/attendanceSignOut", method = RequestMethod.POST)
    ResponseEntity<Object> attendanceSignOut(@RequestBody Object attendanceRequest);

    @RequestMapping(value = "/getAttendanceByUserId", method = RequestMethod.GET)
    ResponseEntity<Object> getAttendanceByUserId(@RequestParam int user_id);

    @RequestMapping(value = "/systemSignOut", method = RequestMethod.PUT)
    ResponseEntity<Object> systemSignOut();

    // Client Controller

    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    ResponseEntity<Object> addClient(@RequestBody Object clientRequest);

    @RequestMapping(value = "/updateClient", method = RequestMethod.POST)
    ResponseEntity<Object> updateClient(@RequestBody Object clientRequest);

    @RequestMapping(value = "/deleteClient", method = RequestMethod.POST)
    ResponseEntity<Object> deleteClient(@RequestParam int clientId);

    @RequestMapping(value = "/getClients", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getClients(@RequestParam(value = "client_id", required = false, defaultValue = "0") int clientId);

    // Grievance Controller

    @RequestMapping(value = "/addGrievance", method = RequestMethod.POST)
    ResponseEntity<Object> addGrievance(@RequestBody Object grievanceRequest);

    @RequestMapping(value = "/getGrievance", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getGrievance(@RequestParam(required = false, defaultValue = "0") long userId,
                                              @RequestParam(required = false, defaultValue = "0") long grievanceId);

    @RequestMapping(value = "/getGemGrievance", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getGemGrievance(@RequestParam(required = false, defaultValue = "0") long userId);

    @RequestMapping(value = "/updateGrievance", method = RequestMethod.PUT)
    ResponseEntity<Object> updateGrievance(@RequestBody Object grievanceUpdateRequest);

    // Invoice Controller

    @RequestMapping(value = "/addInvoice", method = RequestMethod.POST)
    ResponseEntity<Object> addInvoice(@RequestBody Object invoiceRequest);

    @RequestMapping(value = "/getInvoice", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getInvoice(@RequestParam(value = "projectId", required = false, defaultValue = "0") int projectId);

    @RequestMapping(value = "/deleteInvoice", method = RequestMethod.GET)
    ResponseEntity<Object> deleteInvoice(@RequestParam(value = "invoiceId") int invoiceId,
                                    @RequestParam(value = "userId") int userId,
                                    @RequestParam(value = "justification") String justification);

    @RequestMapping(value = "/updateInvoice", method = RequestMethod.PUT)
    ResponseEntity<Object> updateInvoice(@RequestBody Object invoiceUpdateRequest);

    @RequestMapping(value = "/addInvoiceHistory", method = RequestMethod.POST)
    ResponseEntity<Object> addInvoiceHistory(@RequestBody Object invoiceHistoryRequest);

    @RequestMapping(value = "/getDepartmentsByProjectIds", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getDepartmentsByProjectIds(@RequestParam(value = "projectIds") List<Long> projectIds);

    @RequestMapping(value = "/addReceivedInvoice", method = RequestMethod.POST)
    ResponseEntity<Object> addReceivedInvoice(@RequestBody Object invoiceReceivedRequest);

    @RequestMapping(value = "/getInvoiceByProjectId", method = RequestMethod.GET)
    ResponseEntity<Object> getInvoiceByProjectId(@RequestParam(value = "projectId") long projectId);

    @RequestMapping(value = "/getInvoiceSummaryByProjectId", method = RequestMethod.GET)
    ResponseEntity<Object> getInvoiceSummaryByProjectId(@RequestParam(value = "projectId") long projectId);

    @RequestMapping(value = "/getInvoiceHistoryById", method = RequestMethod.GET)
    ResponseEntity<Object> getInvoiceHistoryById(@RequestParam("invoiceHistoryId") long invoiceHistoryId);

    // Leave Controller

    @RequestMapping(value = "/addLeave", method = RequestMethod.POST)
    ResponseEntity<Object> addLeave(@RequestBody Object leaveRequest);

    @RequestMapping(value = "/getLeave", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getLeave(@RequestParam(required = false, defaultValue = "0") long userId,
                                          @RequestParam(required = false, defaultValue = "0") long superiorId,
                                          @RequestParam(required = false, defaultValue = "0") long leaveId);

    @RequestMapping(value = "/getLeaveBalance", method = RequestMethod.GET)
    ResponseEntity<Object> getLeaveBalance(@RequestParam(required = false, defaultValue = "0") long userId);

    @RequestMapping(value = "/updateLeave", method = RequestMethod.PUT)
    ResponseEntity<Object> updateLeave(@RequestBody Object leaveUpdateRequest);

    @RequestMapping(value = "/cancelLeave", method = RequestMethod.POST)
    ResponseEntity<Object> cancelLeave(@RequestParam long leaveId);

    @RequestMapping(value = "/addMonthlyLeaveBalance", method = RequestMethod.PUT)
    ResponseEntity<Object> addMonthlyLeaveBalance();

    @RequestMapping(value = "/getAllLeaveBalance", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getAllLeaveBalance();

    @RequestMapping(value = "/updateLeaveBalance", method = RequestMethod.PUT)
    ResponseEntity<Object> updateLeaveBalance(@RequestBody Object updateLeaveBalanceRequest);

    @RequestMapping(value = "/addLeaveBalance", method = RequestMethod.POST)
    ResponseEntity<Object> addLeaveBalance(@RequestBody Object leaveBalanceRequest);

    @RequestMapping(value = "/updateMassLeaveBalance", method = RequestMethod.PUT)
    ResponseEntity<Object> updateMassLeaveBalance(@RequestBody Object updateMassLeaveBalanceRequest);

    // Master Controller

    @RequestMapping(value = "/getDepartmentMaster", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getDepartmentMaster();

    @RequestMapping(value = "/getDepartmentsForProjectCreation", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getDepartmentsForProjectCreation();

    @RequestMapping(value = "/getRoleMaster", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getRoleMaster();

    @RequestMapping(value = "/getStatusMaster", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getStatusMaster();

    @RequestMapping(value = "/getHolidays", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getHolidays(@RequestParam(value = "templateId") int templateId);

    @RequestMapping(value = "/addHolidays", method = RequestMethod.POST)
    ResponseEntity<Object> addHolidays(@RequestBody Object holidayMasterRequest);

    @RequestMapping(value = "/updateHolidays", method = RequestMethod.PUT)
    ResponseEntity<Object> updateHolidays(@RequestBody Object updateHolidayRequest);

    @RequestMapping(value = "/getGrievanceCategoryMaster", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getGrievanceCategoryMaster();

    @RequestMapping(value = "/addTemplates", consumes = "multipart/form-data", method = RequestMethod.POST)
    ResponseEntity<Object> addTemplates(@RequestParam(value = "name") String name,
                                               @RequestParam(value = "userId") int userId,
                                               @RequestParam(value = "file") MultipartFile file);

    @RequestMapping(value = "/getTemplates", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getTemplatesMaster();

    @RequestMapping(value = "/getOptionalHolidayList", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getOptionalHolidayList();

    @RequestMapping(value = "/addCurrency", method = RequestMethod.POST)
    ResponseEntity<Object> addCurrency(@RequestBody Object currencyMasterRequest);

    @RequestMapping(value = "/updateCurrency", method = RequestMethod.PUT)
    ResponseEntity<Object> updateCurrency(@RequestBody Object updateCurrencyMasterRequest);

    @RequestMapping(value = "/getCurrency", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getCurrency();

    // Notification Controller

    @RequestMapping(value = "/saveNotification", method = RequestMethod.POST)
    ResponseEntity<Object> saveNotification(@RequestBody Object notificationRequest);

    @RequestMapping(value = "/sendSignInNotification", method = RequestMethod.GET)
    ResponseEntity<String> sendSignInNotification();

    @RequestMapping(value = "/sendSignOutNotification", method = RequestMethod.GET)
    ResponseEntity<String> sendSignOutNotification();

    // Overhead Controller

    @RequestMapping(value = "/addOverhead", method = RequestMethod.POST)
    ResponseEntity<Object> addOverhead(@RequestBody Object overheadRequest);

    @RequestMapping(value = "/getOverhead", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getOverhead();

    @RequestMapping(value = "/getMonthlyOverhead", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getMonthlyOverhead(@RequestParam(value = "years") String financialYear);

    @RequestMapping(value = "/updateOverhead", method = RequestMethod.PUT)
    ResponseEntity<?> updateOverhead(@RequestBody Object updateOverheadRequest);

    // Project Controller

    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    ResponseEntity<Object> addProject(@RequestBody Object projectRequest);

    @RequestMapping(value = "/getProjectsOld", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getProjects(@RequestParam(value = "department_id", required = false, defaultValue = "0") int departmentId,
                                             @RequestParam(value = "project_id", required = false, defaultValue = "0") int projectId);

    @RequestMapping(value = "/getProjects", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getProjectsBySP(@RequestParam(value = "department_id", required = false, defaultValue = "0") int departmentId,
                                                 @RequestParam(value = "project_id", required = false, defaultValue = "0") int projectId);

    @RequestMapping(value = "/getProjectsWithProjectCode", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getProjectsWithProjectCode(@RequestParam(value = "department_id", required = false, defaultValue = "0") int departmentId,
                                                            @RequestParam(value = "project_id", required = false, defaultValue = "0") int projectId);

    @PostMapping(value = "/uploadProjectLogo")
    ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file,
                                      @RequestParam("project_id") int projectId);

    @RequestMapping(value = "/updateProjectDetail", method = RequestMethod.PUT)
    ResponseEntity<Object> updateProjectDetail(@RequestBody Object project);

    @RequestMapping(value = "/updateNewEntryInProjectDepartment", method = RequestMethod.PUT)
    ResponseEntity<Object> updateNewProjectDepartmentDetail(@RequestBody Object updateProjectDepartmentRequest);

    @RequestMapping(value = "/updateProjectDepartment", method = RequestMethod.PUT)
    ResponseEntity<Object> updateProjectDeptDetails(@RequestBody Object projectDepartmentRequest);

    @RequestMapping(value = "/getProjectsOfProjectManager", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getProjectsOfProjectManager(@RequestParam(value = "projectManagerId") int projectManagerId);

    @RequestMapping(value = "/getProjectsBySp", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getProjectsBySp(@RequestParam(value = "department_id", required = false, defaultValue = "0") int departmentId,
                                                        @RequestParam(value = "project_id", required = false, defaultValue = "0") int projectId);

    @RequestMapping(value = "/notifyClosingProjects", method = RequestMethod.GET)
    ResponseEntity<Object> notifyClosingProjects();

    // Report Controller

    @RequestMapping(value = "/timesheetReport", method = RequestMethod.POST)
    void exportToExcelTimesheetReport(@RequestBody Object timesheetReportRequest) throws IOException;

    @RequestMapping(value = "/getTimesheetReport", method = RequestMethod.POST)
    ResponseEntity<List<Object>> getTimesheetReport(@RequestBody Object timesheetReportRequest);

    @RequestMapping(value = "/leaveReport", method = RequestMethod.POST)
    void exportToExcelLeaveReport( @RequestBody Object leaveReportRequest) throws IOException;

    @RequestMapping(value = "/getLeaveReport", method = RequestMethod.POST)
    ResponseEntity<List<Object>> getLeaveReport(@RequestBody Object leaveReportRequest);

    @RequestMapping(value = "/attendanceReport", method = RequestMethod.POST)
    void exportToExcelAttendanceReport(@RequestBody Object timesheetReportRequest) throws IOException;

    @RequestMapping(value = "/getAttendanceReport", method = RequestMethod.POST)
    ResponseEntity<List<Object>> getAttendanceReport(@RequestBody Object timesheetReportRequest);

    @RequestMapping(value = "/getProjectsForReport", method = RequestMethod.POST)
    ResponseEntity<List<Object>> getProjectsForReport(@RequestBody Object getProjectRequest);

    @RequestMapping(value = "/getManagersByDeptForReport", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getProjectManagerRoleUsersByDeptIdForReport(@RequestParam(value = "deptId") List<Integer> deptIds);

    @RequestMapping(value = "/getUsersByProjectAndDeptIdForReport", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getUsersByProjectAndDeptIdForReport(@RequestParam(value = "projectId") List<Long> projectIds,
                                                                            @RequestParam(value = "departmentIds") List<Long> departmentIds);

    @RequestMapping(value = "/getSuperiorUsersForReport", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getSuperiorUsersForReport();

    @RequestMapping(value = "/projectWiseTimesheetReport", method = RequestMethod.POST)
    void projectWiseTimesheetReport(@RequestBody Object timesheetReportRequest) throws IOException;

    @RequestMapping(value = "/employeeStatusReport", method = RequestMethod.POST)
    void exportToExcelEmployeeStatusReport(@RequestBody Object employeeStatusReportRequest) throws IOException;

    @RequestMapping(value = "/employeeDetailsReport", method = RequestMethod.POST)
    void exportToExcelEmployeeDetailsReport(@RequestBody Object employeeDetailsReportRequest) throws IOException;

    @RequestMapping(value = "/getEmployeeDetailsReport", method = RequestMethod.POST)
    ResponseEntity<List<Object>> getEmployeeDetailsReport(@RequestBody Object employeeDetailsReportRequest);

    @RequestMapping(value = "/getProjectsByDepartmentIdsForReport", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getProjectsByDepartmentIdsForReport(@RequestParam(value = "departmentIds") List<Long> departmentIds);

    @RequestMapping(value = "/generateWeeklyTimesheetSummaryReports", method = RequestMethod.GET)
    ResponseEntity<Object> generateWeeklyTimesheetSummaryReports();

    @RequestMapping(value = "/overheadReport", method = RequestMethod.POST)
    ResponseEntity<Object> exportToExcelOverheadReport(@RequestBody Object departmentWiseOverheadReportRequest) throws IOException;

    @RequestMapping(value = "/getOverheadReport", method = RequestMethod.POST)
    ResponseEntity<List<Object>> getOverheadReport(@RequestBody Object departmentWiseOverheadReportRequest);

    @RequestMapping(value = "/profitabilityReport", method = RequestMethod.POST)
    ResponseEntity<Object> getProfitabilityReport(@RequestBody Object profitabilityRequest);

    @RequestMapping(value = "/profitabilityReportDownload", method = RequestMethod.POST)
    ResponseEntity<Object> getProfitabilityReportDownload(@RequestBody Object profitabilityRequest);

    // Timesheet Controller

    @RequestMapping(value = "/saveTimesheet", method = RequestMethod.POST)
    ResponseEntity<List<Object>> saveTimesheet(@RequestBody Object timesheetRequest);

    @RequestMapping(value = "/getTimesheet", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getTimesheet(@RequestParam  int user_id);

    @RequestMapping(value = "/getTimesheetByUserIdAndProjectManagerId", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getTimesheetByUserIdAndProjectManagerId(@RequestParam  List<Long> userIds,
                                                                         @RequestParam  long projectManagerId);

    @RequestMapping(value = "/getTimesheetByProject", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getTimesheetByProject(@RequestParam  long projectManagerId);

    @RequestMapping(value = "/updateTimesheet", method = RequestMethod.PUT)
    ResponseEntity<Object> updateTimesheet(@RequestBody Object timesheet);

    @RequestMapping(value = "/updateAllTimesheet", method = RequestMethod.PUT)
    ResponseEntity<Object> updateAllTimesheet(@RequestBody Object updateTimesheetRequest);

    @RequestMapping(value = "/autoApprovedTimesheetOfHods", method = RequestMethod.PUT)
    ResponseEntity<Object> autoApprovedTimesheetOfHods();

    @RequestMapping(value = "/getUsersListNotFilledTimesheet", method = RequestMethod.GET)
    ResponseEntity<Object> getUsersListNotFilledTimesheet();

    @RequestMapping(value = "/saveMissedTimesheet", method = RequestMethod.POST)
    ResponseEntity<Object> saveMissedTimesheet(@RequestBody Object missedTimesheetRequest);

    @RequestMapping(value = "/updateMissedTimesheet", method = RequestMethod.PUT)
    ResponseEntity<Object> updateMissedTimesheet(@RequestBody Object updateMissedTimesheetRequest);

    @RequestMapping(value = "/getMissedTimesheet", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getMissedTimesheet(@RequestParam  int userId);

    @RequestMapping(value = "/getMissedTimesheetUsers", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getMissedTimesheetUsers(@RequestParam(value = "projectManagerId") int projectManagerId);

    // USer Controller

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    ResponseEntity<Object> createAuthenticationToken(@RequestBody Object authenticationRequest) throws Exception;

    @RequestMapping(value = "user/validateOtp", method = RequestMethod.POST)
    ResponseEntity<Object> validateOtp(@RequestBody Object otpRequest) throws Exception;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    ResponseEntity<Object> addUser(@RequestBody Object userRequest) throws NoSuchAlgorithmException;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getUsers(@RequestParam(value = "departmentId", required = false, defaultValue = "0") int departmentId,
                                                 @RequestParam(value = "userId", required = false, defaultValue = "0") int userId,
                                                 @RequestParam(value = "active", required = false, defaultValue = "1") int active);

    @RequestMapping(value = "/userLogout", method = RequestMethod.GET)
    ResponseEntity<Object> userLogout(@RequestParam("userId") int userId, @RequestParam("platform") String platform);

    @RequestMapping(value = "/amIManager", method = RequestMethod.GET)
    ResponseEntity<Object> checkManagerStatus(@RequestParam("userId") int userId);

    @RequestMapping(value = "/monthlyStatus", method = RequestMethod.GET)
    ResponseEntity<Object> checkMonthlyStatus(@RequestParam("userId") int userId,
                                                     @RequestParam("month") int month,
                                                     @RequestParam("year") int year);

    @PostMapping(value = "/uploadUsersBulk", consumes = "multipart/form-data")
    ResponseEntity<Object> uploadUsers(@RequestParam(value = "file") MultipartFile file);

    @RequestMapping(value = "/updateUserDetail", method = RequestMethod.PUT)
    ResponseEntity<Object> updateUserDetail(@RequestBody Object user);

    @PostMapping(value = "/uploadUserProfile")
    ResponseEntity<Object> uploadUserProfile(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("user_id") int userId);

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    ResponseEntity<Object> deleteUser(@RequestBody Object deleteUserRequest);

    @RequestMapping(value = "/getUsersOfProject", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getUsersOfProject(@RequestParam(value = "projectManagerId") int projectManagerId);

    @RequestMapping(value = "/amISuperior", method = RequestMethod.GET)
    ResponseEntity<Object> checkSuperiorStatus(@RequestParam("userId") int userId);

    @RequestMapping(value = "/amIAdmin", method = RequestMethod.GET)
    ResponseEntity<Object> checkAdminStatus(@RequestParam("userId") int userId);

    @RequestMapping(value = "/getManagersByDept", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getProjectManagerRoleUsersByDeptId(@RequestParam(value = "deptId") int deptId);

    @RequestMapping(value = "/getUsersByProjectAndDeptId", method = RequestMethod.GET)
    ResponseEntity<List<Object>> getUsersByProjectAndDeptId(@RequestParam(value = "projectId") int projectId,
                                                                   @RequestParam(value = "departmentId") int departmentId);

    @RequestMapping(value = "/updateDeviceToken", method = RequestMethod.PUT)
    ResponseEntity<Object> updateDeviceToken(@RequestParam(value = "userId") int userId,
                                                    @RequestParam(value = "deviceToken") String deviceToken);

    @RequestMapping(value = "/getUserNamesByDeptIds", method = RequestMethod.GET)
    ResponseEntity<Object> getUserNamesByDeptIds(@RequestParam(value = "departmentIds") List<Integer> departmentIds);

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    ResponseEntity<Object> registerUser(@RequestBody(required = false) Object userRegisterRequest);
}
