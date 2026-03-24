package com.gemengserv.bff.controller;

import com.gemengserv.bff.service.LvmService;
import org.bouncycastle.asn1.cmp.OOBCertHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lvm")
public class LvmController
{
    @Autowired
    private LvmService lvmService;

    // Attendance Controller

    @RequestMapping(value = "/attendanceSignIn", method = RequestMethod.POST)
    public ResponseEntity<Object> attendanceSignIn(@RequestBody Object attendanceRequest)
    {
        return lvmService.attendanceSignIn(attendanceRequest);
    }

    @RequestMapping(value = "/attendanceSignOut", method = RequestMethod.POST)
    public ResponseEntity<Object> attendanceSignOut(@RequestBody Object attendanceRequest)
    {
        return lvmService.attendanceSignOut(attendanceRequest);
    }

    @RequestMapping(value = "/getAttendanceByUserId", method = RequestMethod.GET)
    public ResponseEntity<Object> getAttendanceByUserId(@RequestParam int user_id)
    {
        return lvmService.getAttendanceByUserId(user_id);
    }

    @RequestMapping(value = "/systemSignOut", method = RequestMethod.PUT)
    public ResponseEntity<Object> systemSignOut()
    {
        return lvmService.systemSignOut();
    }

    // Client Controller

    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public ResponseEntity<Object> addClient(@RequestBody Object clientRequest)
    {
        return lvmService.addClient(clientRequest);
    }

    @RequestMapping(value = "/updateClient", method = RequestMethod.POST)
    public ResponseEntity<Object> updateClient(@RequestBody Object clientRequest)
    {
        return lvmService.updateClient(clientRequest);
    }

    @RequestMapping(value = "/deleteClient", method = RequestMethod.POST)
    public ResponseEntity<Object> deleteClient(@RequestParam int clientId)
    {
        return lvmService.deleteClient(clientId);
    }

    @RequestMapping(value = "/getClients", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getClients(@RequestParam(value = "client_id", required = false, defaultValue = "0") int clientId)
    {
        return lvmService.getClients(clientId);
    }

    // Grievance Controller

    @RequestMapping(value = "/addGrievance", method = RequestMethod.POST)
    public ResponseEntity<Object> addGrievance(@RequestBody Object grievanceRequest)
    {
        return lvmService.addGrievance(grievanceRequest);
    }

    @RequestMapping(value = "/getGrievance", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getGrievance(@RequestParam(required = false, defaultValue = "0") long userId,
                                                     @RequestParam(required = false, defaultValue = "0") long grievanceId)
    {
        return lvmService.getGrievance(userId, grievanceId);
    }

    @RequestMapping(value = "/getGemGrievance", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getGemGrievance(@RequestParam(required = false, defaultValue = "0") long userId)
    {
        return lvmService.getGemGrievance(userId);
    }

    @RequestMapping(value = "/updateGrievance", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateGrievance(@RequestBody Object grievanceUpdateRequest)
    {
        return lvmService.updateGrievance(grievanceUpdateRequest);
    }

    // Invoice Controller

    @RequestMapping(value = "/addInvoice", method = RequestMethod.POST)
    public ResponseEntity<Object> addInvoice(@RequestBody Object invoiceRequest)
    {
        return lvmService.addInvoice(invoiceRequest);
    }

    @RequestMapping(value = "/getInvoice", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getInvoice(@RequestParam(value = "projectId", required = false, defaultValue = "0") int projectId)
    {
        return lvmService.getInvoice(projectId);
    }

    @RequestMapping(value = "/deleteInvoice", method = RequestMethod.GET)
    public ResponseEntity<Object> deleteInvoice(@RequestParam(value = "invoiceId") int invoiceId,
                                                @RequestParam(value = "userId") int userId,
                                                @RequestParam(value = "justification") String justification)
    {
        return lvmService.deleteInvoice(invoiceId, userId, justification);
    }

    @RequestMapping(value = "/updateInvoice", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateInvoice(@RequestBody Object invoiceUpdateRequest)
    {
        return lvmService.updateInvoice(invoiceUpdateRequest);
    }

    @RequestMapping(value = "/addInvoiceHistory", method = RequestMethod.POST)
    public ResponseEntity<Object> addInvoiceHistory(@RequestBody Object invoiceHistoryRequest)
    {
        return lvmService.addInvoiceHistory(invoiceHistoryRequest);
    }

    @RequestMapping(value = "/getDepartmentsByProjectIds", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getDepartmentsByProjectIds(@RequestParam(value = "projectIds") List<Long> projectIds)
    {
        return lvmService.getDepartmentsByProjectIds(projectIds);
    }

    @RequestMapping(value = "/addReceivedInvoice", method = RequestMethod.POST)
    public ResponseEntity<Object> addReceivedInvoice(@RequestBody Object invoiceReceivedRequest)
    {
        return lvmService.addReceivedInvoice(invoiceReceivedRequest);
    }

    @RequestMapping(value = "/getInvoiceByProjectId", method = RequestMethod.GET)
    public ResponseEntity<Object> getInvoiceByProjectId(@RequestParam(value = "projectId") long projectId)
    {
        return lvmService.getInvoiceByProjectId(projectId);
    }

    @RequestMapping(value = "/getInvoiceSummaryByProjectId", method = RequestMethod.GET)
    public ResponseEntity<Object> getInvoiceSummaryByProjectId(@RequestParam(value = "projectId") long projectId)
    {
        return lvmService.getInvoiceSummaryByProjectId(projectId);
    }

    @RequestMapping(value = "/getInvoiceHistoryById", method = RequestMethod.GET)
    public ResponseEntity<Object> getInvoiceHistoryById(@RequestParam("invoiceHistoryId") long invoiceHistoryId)
    {
        return lvmService.getInvoiceHistoryById(invoiceHistoryId);
    }

    // Leave Controller

    @RequestMapping(value = "/addLeave", method = RequestMethod.POST)
    public ResponseEntity<Object> addLeave(@RequestBody Object leaveRequest)
    {
        return lvmService.addLeave(leaveRequest);
    }

    @RequestMapping(value = "/getLeave", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getLeave(@RequestParam(required = false, defaultValue = "0") long userId,
                                                 @RequestParam(required = false, defaultValue = "0") long superiorId,
                                                 @RequestParam(required = false, defaultValue = "0") long leaveId)
    {
        return lvmService.getLeave(userId, superiorId, leaveId);
    }

    @RequestMapping(value = "/getLeaveBalance", method = RequestMethod.GET)
    public ResponseEntity<Object> getLeaveBalance(@RequestParam(required = false, defaultValue = "0") long userId)
    {
        return lvmService.getLeaveBalance(userId);
    }

    @RequestMapping(value = "/updateLeave", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateLeave(@RequestBody Object leaveUpdateRequest)
    {
        return lvmService.updateLeave(leaveUpdateRequest);
    }

    @RequestMapping(value = "/cancelLeave", method = RequestMethod.POST)
    public ResponseEntity<Object> cancelLeave(@RequestParam long leaveId)
    {
        return lvmService.cancelLeave(leaveId);
    }

    @RequestMapping(value = "/addMonthlyLeaveBalance", method = RequestMethod.PUT)
    public ResponseEntity<Object> addMonthlyLeaveBalance()
    {
        return lvmService.addMonthlyLeaveBalance();
    }

    @RequestMapping(value = "/getAllLeaveBalance", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getAllLeaveBalance()
    {
        return lvmService.getAllLeaveBalance();
    }

    @RequestMapping(value = "/updateLeaveBalance", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateLeaveBalance(@RequestBody Object updateLeaveBalanceRequest)
    {
        return lvmService.updateLeaveBalance(updateLeaveBalanceRequest);
    }

    @RequestMapping(value = "/addLeaveBalance", method = RequestMethod.POST)
    public ResponseEntity<Object> addLeaveBalance(@RequestBody Object leaveBalanceRequest)
    {
        return lvmService.addLeaveBalance(leaveBalanceRequest);
    }

    @RequestMapping(value = "/updateMassLeaveBalance", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateMassLeaveBalance(@RequestBody Object updateMassLeaveBalanceRequest)
    {
        return lvmService.updateMassLeaveBalance(updateMassLeaveBalanceRequest);
    }

    // Master Controller

    @RequestMapping(value = "/getDepartmentMaster", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getDepartmentMaster()
    {
        return lvmService.getDepartmentMaster();
    }

    @RequestMapping(value = "/getDepartmentsForProjectCreation", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getDepartmentsForProjectCreation()
    {
        return lvmService.getDepartmentsForProjectCreation();
    }

    @RequestMapping(value = "/getRoleMaster", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getRoleMaster()
    {
        return lvmService.getRoleMaster();
    }

    @RequestMapping(value = "/getStatusMaster", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getStatusMaster()
    {
        return lvmService.getStatusMaster();
    }

    @RequestMapping(value = "/getHolidays", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getHolidays(@RequestParam(value = "templateId") int templateId)
    {
        return lvmService.getHolidays(templateId);
    }

    @RequestMapping(value = "/addHolidays", method = RequestMethod.POST)
    public ResponseEntity<Object> addHolidays(@RequestBody Object holidayMasterRequest)
    {
        return lvmService.addHolidays(holidayMasterRequest);
    }

    @RequestMapping(value = "/updateHolidays", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateHolidays(@RequestBody Object updateHolidayRequest)
    {
        return lvmService.updateHolidays(updateHolidayRequest);
    }

    @RequestMapping(value = "/getGrievanceCategoryMaster", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getGrievanceCategoryMaster()
    {
        return lvmService.getGrievanceCategoryMaster();
    }

    @RequestMapping(value = "/addTemplates", consumes = "multipart/form-data", method = RequestMethod.POST)
    public ResponseEntity<Object> addTemplates(@RequestParam(value = "name") String name,
                                               @RequestParam(value = "userId") int userId,
                                               @RequestParam(value = "file") MultipartFile file)
    {
        return lvmService.addTemplates(name, userId, file);
    }

    @RequestMapping(value = "/getTemplates", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getTemplatesMaster()
    {
        return lvmService.getTemplatesMaster();
    }

    @RequestMapping(value = "/getOptionalHolidayList", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getOptionalHolidayList()
    {
        return lvmService.getOptionalHolidayList();
    }

    @RequestMapping(value = "/addCurrency", method = RequestMethod.POST)
    public ResponseEntity<Object> addCurrency(@RequestBody Object currencyMasterRequest)
    {
        return lvmService.addCurrency(currencyMasterRequest);
    }

    @RequestMapping(value = "/updateCurrency", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCurrency(@RequestBody Object updateCurrencyMasterRequest)
    {
        return lvmService.updateCurrency(updateCurrencyMasterRequest);
    }

    @RequestMapping(value = "/getCurrency", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getCurrency()
    {
        return lvmService.getCurrency();
    }

    // Notification Controller

    @RequestMapping(value = "/saveNotification", method = RequestMethod.POST)
    public ResponseEntity<Object> saveNotification(@RequestBody Object notificationRequest)
    {
        return lvmService.saveNotification(notificationRequest);
    }

    @RequestMapping(value = "/sendSignInNotification", method = RequestMethod.GET)
    public ResponseEntity<String> sendSignInNotification()
    {
        return lvmService.sendSignInNotification();
    }

    @RequestMapping(value = "/sendSignOutNotification", method = RequestMethod.GET)
    public ResponseEntity<String> sendSignOutNotification()
    {
        return lvmService.sendSignOutNotification();
    }

    // Overhead Controller

    @RequestMapping(value = "/addOverhead", method = RequestMethod.POST)
    public ResponseEntity<Object> addOverhead(@RequestBody Object overheadRequest)
    {
        return lvmService.addOverhead(overheadRequest);
    }

    @RequestMapping(value = "/getOverhead", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getOverhead()
    {
        return lvmService.getOverhead();
    }

    @RequestMapping(value = "/getMonthlyOverhead", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getMonthlyOverhead(@RequestParam(value = "years") String financialYear)
    {
        return lvmService.getMonthlyOverhead(financialYear);
    }

    @RequestMapping(value = "/updateOverhead", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOverhead(@RequestBody Object updateOverheadRequest)
    {
        return lvmService.updateOverhead(updateOverheadRequest);
    }

    // Project Controller

    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public ResponseEntity<Object> addProject(@RequestBody Object projectRequest)
    {
        return lvmService.addProject(projectRequest);
    }

    @RequestMapping(value = "/getProjectsOld", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getProjects(@RequestParam(value = "department_id", required = false, defaultValue = "0") int departmentId,
                                                    @RequestParam(value = "project_id", required = false, defaultValue = "0") int projectId)
    {
        return lvmService.getProjects(departmentId, projectId);
    }

    @RequestMapping(value = "/getProjects", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getProjectsBySP(@RequestParam(value = "department_id", required = false, defaultValue = "0") int departmentId,
                                                         @RequestParam(value = "project_id", required = false, defaultValue = "0") int projectId)
    {
        return lvmService.getProjects(departmentId,projectId);
    }

    @RequestMapping(value = "/getProjectsWithProjectCode", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getProjectsWithProjectCode(@RequestParam(value = "department_id", required = false, defaultValue = "0") int departmentId,
                                                                    @RequestParam(value = "project_id", required = false, defaultValue = "0") int projectId)
    {
        return lvmService.getProjectsWithProjectCode(departmentId, projectId);
    }

    @PostMapping(value = "/uploadProjectLogo")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("project_id") int projectId)
    {
        return lvmService.uploadFile(file, projectId);
    }

    @RequestMapping(value = "/updateProjectDetail", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProjectDetail(@RequestBody Object project)
    {
        return lvmService.updateProjectDetail(project);
    }

    @RequestMapping(value = "/updateNewEntryInProjectDepartment", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateNewProjectDepartmentDetail(@RequestBody Object updateProjectDepartmentRequest)
    {
        return lvmService.updateNewProjectDepartmentDetail(updateProjectDepartmentRequest);
    }

    @RequestMapping(value = "/updateProjectDepartment", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProjectDeptDetails(@RequestBody Object projectDepartmentRequest)
    {
        return lvmService.updateProjectDeptDetails(projectDepartmentRequest);
    }

    @RequestMapping(value = "/getProjectsOfProjectManager", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getProjectsOfProjectManager(@RequestParam(value = "projectManagerId") int projectManagerId)
    {
        return lvmService.getProjectsOfProjectManager(projectManagerId);
    }

    @RequestMapping(value = "/getProjectsBySp", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getProjectsBySp(@RequestParam(value = "department_id", required = false, defaultValue = "0") int departmentId,
                                                        @RequestParam(value = "project_id", required = false, defaultValue = "0") int projectId)
    {
        return lvmService.getProjectsBySp(departmentId, projectId);
    }

    @RequestMapping(value = "/notifyClosingProjects", method = RequestMethod.GET)
    public ResponseEntity<Object> notifyClosingProjects()
    {
        return lvmService.notifyClosingProjects();
    }

    // Report Controller

    @RequestMapping(value = "/timesheetReport", method = RequestMethod.POST)
    public void exportToExcelTimesheetReport(HttpServletResponse httpServletResponse,
                                             @RequestBody Object timesheetReportRequest) throws IOException
    {
        lvmService.exportToExcelTimesheetReport(timesheetReportRequest);
    }

    @RequestMapping(value = "/getTimesheetReport", method = RequestMethod.POST)
    public ResponseEntity<List<Object>> getTimesheetReport(@RequestBody Object timesheetReportRequest)
    {
        return lvmService.getTimesheetReport(timesheetReportRequest);
    }

    @RequestMapping(value = "/leaveReport", method = RequestMethod.POST)
    public void exportToExcelLeaveReport(HttpServletResponse httpServletResponse,
                                         @RequestBody Object leaveReportRequest) throws IOException
    {
        lvmService.exportToExcelLeaveReport( leaveReportRequest);
    }

    @RequestMapping(value = "/getLeaveReport", method = RequestMethod.POST)
    public ResponseEntity<List<Object>> getLeaveReport(@RequestBody Object leaveReportRequest)
    {
       return lvmService.getLeaveReport(leaveReportRequest);
    }

    @RequestMapping(value = "/attendanceReport", method = RequestMethod.POST)
    public void exportToExcelAttendanceReport(HttpServletResponse httpServletResponse,
                                              @RequestBody Object timesheetReportRequest) throws IOException
    {
        lvmService.exportToExcelAttendanceReport( timesheetReportRequest);
    }

    @RequestMapping(value = "/getAttendanceReport", method = RequestMethod.POST)
    public ResponseEntity<List<Object>> getAttendanceReport(@RequestBody Object timesheetReportRequest)
    {
       return lvmService.getAttendanceReport(timesheetReportRequest);
    }

    @RequestMapping(value = "/getProjectsForReport", method = RequestMethod.POST)
    public ResponseEntity<List<Object>> getProjectsForReport(@RequestBody Object getProjectRequest)
    {
        return lvmService.getProjectsForReport(getProjectRequest);
    }

    @RequestMapping(value = "/getManagersByDeptForReport", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getProjectManagerRoleUsersByDeptIdForReport(@RequestParam(value = "deptId") List<Integer> deptIds)
    {
        return lvmService.getProjectManagerRoleUsersByDeptIdForReport(deptIds);
    }

    @RequestMapping(value = "/getUsersByProjectAndDeptIdForReport", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getUsersByProjectAndDeptIdForReport(@RequestParam(value = "projectId") List<Long> projectIds,
                                                                          @RequestParam(value = "departmentIds") List<Long> departmentIds)
    {
        return lvmService.getUsersByProjectAndDeptIdForReport(projectIds,departmentIds);
    }

    @RequestMapping(value = "/getSuperiorUsersForReport", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getSuperiorUsersForReport()
    {
        return lvmService.getSuperiorUsersForReport();
    }

    @RequestMapping(value = "/projectWiseTimesheetReport", method = RequestMethod.POST)
    public void projectWiseTimesheetReport(HttpServletResponse httpServletResponse,
                                           @RequestBody Object timesheetReportRequest) throws IOException
    {
        lvmService.projectWiseTimesheetReport( timesheetReportRequest);
    }

    @RequestMapping(value = "/employeeStatusReport", method = RequestMethod.POST)
    public void exportToExcelEmployeeStatusReport(HttpServletResponse httpServletResponse,
                                                  @RequestBody Object employeeStatusReportRequest) throws IOException
    {
        lvmService.exportToExcelEmployeeStatusReport( employeeStatusReportRequest);
    }

    @RequestMapping(value = "/employeeDetailsReport", method = RequestMethod.POST)
    public void exportToExcelEmployeeDetailsReport(HttpServletResponse httpServletResponse,
                                                   @RequestBody Object employeeDetailsReportRequest) throws IOException
    {
        lvmService.exportToExcelEmployeeDetailsReport( employeeDetailsReportRequest);
    }

    @RequestMapping(value = "/getEmployeeDetailsReport", method = RequestMethod.POST)
    public ResponseEntity<List<Object>> getEmployeeDetailsReport(@RequestBody Object employeeDetailsReportRequest)
    {
        return lvmService.getEmployeeDetailsReport(employeeDetailsReportRequest);
    }

    @RequestMapping(value = "/getProjectsByDepartmentIdsForReport", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getProjectsByDepartmentIdsForReport(@RequestParam(value = "departmentIds") List<Long> departmentIds)
    {
        return lvmService.getProjectsByDepartmentIdsForReport(departmentIds);
    }

    @RequestMapping(value = "/generateWeeklyTimesheetSummaryReports", method = RequestMethod.GET)
    public ResponseEntity<Object> generateWeeklyTimesheetSummaryReports()
    {
        return lvmService.generateWeeklyTimesheetSummaryReports();
    }

    @RequestMapping(value = "/overheadReport", method = RequestMethod.POST)
    public ResponseEntity<Object> exportToExcelOverheadReport(HttpServletResponse httpServletResponse,
                                                         @RequestBody Object departmentWiseOverheadReportRequest) throws IOException
    {
        return lvmService.exportToExcelOverheadReport( departmentWiseOverheadReportRequest);
    }

    @RequestMapping(value = "/getOverheadReport", method = RequestMethod.POST)
    public ResponseEntity<List<Object>> getOverheadReport(@RequestBody Object departmentWiseOverheadReportRequest)
    {
        return lvmService.getOverheadReport(departmentWiseOverheadReportRequest);
    }

    @RequestMapping(value = "/profitabilityReport", method = RequestMethod.POST)
    public ResponseEntity<Object> getProfitabilityReport(@RequestBody Object profitabilityRequest)
    {
        return lvmService.getProfitabilityReport(profitabilityRequest);
    }

    @RequestMapping(value = "/profitabilityReportDownload", method = RequestMethod.POST)
    public ResponseEntity<Object> getProfitabilityReportDownload(HttpServletResponse httpServletResponse,
                                                                 @RequestBody Object profitabilityRequest)
    {
        return lvmService.getProfitabilityReportDownload( profitabilityRequest);
    }

    // Timesheet Controller

    @RequestMapping(value = "/saveTimesheet", method = RequestMethod.POST)
    public ResponseEntity<List<Object>> saveTimesheet(@RequestBody Object timesheetRequest)
    {
        return lvmService.saveTimesheet(timesheetRequest);
    }

    @RequestMapping(value = "/getTimesheet", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getTimesheet(@RequestParam  int user_id)
    {
        return lvmService.getTimesheet(user_id);
    }

    @RequestMapping(value = "/getTimesheetByUserIdAndProjectManagerId", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getTimesheetByUserIdAndProjectManagerId(@RequestParam  List<Long> userIds,
                                                                                @RequestParam  long projectManagerId)
    {
        return lvmService.getTimesheetByUserIdAndProjectManagerId(userIds, projectManagerId);
    }

    @RequestMapping(value = "/getTimesheetByProject", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getTimesheetByProject(@RequestParam  long projectManagerId)
    {
        return lvmService.getTimesheetByProject(projectManagerId);
    }

    @RequestMapping(value = "/updateTimesheet", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTimesheet(@RequestBody Object timesheet)
    {
        return lvmService.updateTimesheet(timesheet);
    }

    @RequestMapping(value = "/updateAllTimesheet", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAllTimesheet(@RequestBody Object updateTimesheetRequest)
    {
        return lvmService.updateAllTimesheet(updateTimesheetRequest);
    }

    @RequestMapping(value = "/autoApprovedTimesheetOfHods", method = RequestMethod.PUT)
    public ResponseEntity<Object> autoApprovedTimesheetOfHods()
    {
        return lvmService.autoApprovedTimesheetOfHods();
    }

    @RequestMapping(value = "/getUsersListNotFilledTimesheet", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsersListNotFilledTimesheet()
    {
        return lvmService.getUsersListNotFilledTimesheet();
    }

    @RequestMapping(value = "/saveMissedTimesheet", method = RequestMethod.POST)
    public ResponseEntity<Object> saveMissedTimesheet(@RequestBody Object missedTimesheetRequest)
    {
        return lvmService.saveMissedTimesheet(missedTimesheetRequest);
    }

    @RequestMapping(value = "/updateMissedTimesheet", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateMissedTimesheet(@RequestBody Object updateMissedTimesheetRequest)
    {
        return lvmService.updateMissedTimesheet(updateMissedTimesheetRequest);
    }

    @RequestMapping(value = "/getMissedTimesheet", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getMissedTimesheet(@RequestParam  int userId)
    {
        return lvmService.getTimesheet(userId);
    }

    @RequestMapping(value = "/getMissedTimesheetUsers", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getMissedTimesheetUsers(@RequestParam(value = "projectManagerId") int projectManagerId)
    {
        return lvmService.getMissedTimesheetUsers(projectManagerId);
    }

    // USer Controller

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody Object authenticationRequest) throws Exception
    {
        return lvmService.createAuthenticationToken(authenticationRequest);
    }

    @RequestMapping(value = "user/validateOtp", method = RequestMethod.POST)
    public ResponseEntity<Object> validateOtp(@RequestBody Object otpRequest) throws Exception
    {
        return lvmService.validateOtp(otpRequest);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<Object> addUser(@RequestBody Object userRequest) throws NoSuchAlgorithmException
    {
        return lvmService.addUser(userRequest);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getUsers(@RequestParam(value = "departmentId", required = false, defaultValue = "0") int departmentId,
                                               @RequestParam(value = "userId", required = false, defaultValue = "0") int userId,
                                               @RequestParam(value = "active", required = false, defaultValue = "1") int active)
    {
        return lvmService.getUsers(departmentId, userId, active);
    }

    @RequestMapping(value = "/userLogout", method = RequestMethod.GET)
    public ResponseEntity<Object> userLogout(@RequestParam("userId") int userId, @RequestParam("platform") String platform)
    {
        return lvmService.userLogout(userId, platform);
    }

    @RequestMapping(value = "/amIManager", method = RequestMethod.GET)
    public ResponseEntity<Object> checkManagerStatus(@RequestParam("userId") int userId)
    {
        return lvmService.checkManagerStatus(userId);
    }

    @RequestMapping(value = "/monthlyStatus", method = RequestMethod.GET)
    public ResponseEntity<Object> checkMonthlyStatus(@RequestParam("userId") int userId,
                                                @RequestParam("month") int month,
                                                @RequestParam("year") int year)
    {
        return lvmService.checkMonthlyStatus(userId, month, year);
    }

    @PostMapping(value = "/uploadUsersBulk", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadUsers(@RequestParam(value = "file") MultipartFile file)
    {
        return lvmService.uploadUsers(file);
    }

    @RequestMapping(value = "/updateUserDetail", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUserDetail(@RequestBody Object user)
    {
        return lvmService.updateUserDetail(user);
    }

    @PostMapping(value = "/uploadUserProfile")
    public ResponseEntity<Object> uploadUserProfile(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("user_id") int userId)
    {
        return lvmService.uploadUserProfile(file, userId);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@RequestBody Object deleteUserRequest)
    {
        return lvmService.deleteUser(deleteUserRequest);
    }

    @RequestMapping(value = "/getUsersOfProject", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getUsersOfProject(@RequestParam(value = "projectManagerId") int projectManagerId)
    {
        return lvmService.getUsersOfProject(projectManagerId);
    }

    @RequestMapping(value = "/amISuperior", method = RequestMethod.GET)
    public ResponseEntity<Object> checkSuperiorStatus(@RequestParam("userId") int userId)
    {
        return lvmService.checkSuperiorStatus(userId);
    }

    @RequestMapping(value = "/amIAdmin", method = RequestMethod.GET)
    public ResponseEntity<Object> checkAdminStatus(@RequestParam("userId") int userId)
    {
        return lvmService.checkAdminStatus(userId);
    }

    @RequestMapping(value = "/getManagersByDept", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getProjectManagerRoleUsersByDeptId(@RequestParam(value = "deptId") int deptId)
    {
        return lvmService.getProjectManagerRoleUsersByDeptId(deptId);
    }

    @RequestMapping(value = "/getUsersByProjectAndDeptId", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getUsersByProjectAndDeptId(@RequestParam(value = "projectId") int projectId,
                                                                 @RequestParam(value = "departmentId") int departmentId)
    {
        return lvmService.getUsersByProjectAndDeptId(projectId, departmentId);
    }

    @RequestMapping(value = "/updateDeviceToken", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateDeviceToken(@RequestParam(value = "userId") int userId,
                                               @RequestParam(value = "deviceToken") String deviceToken)
    {
        return lvmService.updateDeviceToken(userId, deviceToken);
    }

    @RequestMapping(value = "/getUserNamesByDeptIds", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserNamesByDeptIds(@RequestParam(value = "departmentIds") List<Integer> departmentIds)
    {
        return lvmService.getUserNamesByDeptIds(departmentIds);
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ResponseEntity<Object> registerUser(@RequestBody(required = false) Object userRegisterRequest)
    {
        return lvmService.registerUser(userRegisterRequest);
    }


}
