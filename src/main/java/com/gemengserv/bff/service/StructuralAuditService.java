package com.gemengserv.bff.service;


import com.gemengserv.bff.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@FeignClient(name = "structuralaudit", url = "http://localhost:8098", configuration = FeignConfig.class)
public interface StructuralAuditService
{
    // File Upload Controller

    @PostMapping(value = "/upload")
    Object uploadFile(@RequestParam("file") MultipartFile file,
                      @RequestParam("obsId") int obsId,
                      @RequestParam("mediaType") String  mediaType);

    // Location Master Controller

    @GetMapping("/locations/{projectId}")
    List<Object> getLocations(@PathVariable(value = "projectId") int projectId);

    @GetMapping("/locations/relatedTo/{relatedToId}")
    List<Object> getLocationsByRelatedTo(@PathVariable(value = "relatedToId") int relatedToId);

    // Master controller

    @GetMapping(value = "/masters")
    Object getAllMasters();

    // Observation controller

    @PostMapping(value = "/observation")
    Object saveObservation(@RequestBody Object observationDto);

    @GetMapping(value = "/observation")
    List<Object> getObservations();

    @GetMapping(value = "/observation/{id}")
    Object getObservationById(@PathVariable(value = "id") long id);

    @GetMapping("/observationsByLocation/{level1Id}")
    List<Object> getObservationByLevel1Id(@PathVariable(value = "level1Id") int level1Id);

    @GetMapping("/observationsByProjectId/{projectId}")
    List<Object> getObservationByProjectId(@PathVariable(value = "projectId") int projectId);

    @GetMapping("/observationsByLocationId/{locationId}")
    List<Object> getObservationByLocationId(@PathVariable(value = "locationId") int locationId);

    // Qc Observation Controller

    @PostMapping(value = "/qcObservation")
    Object saveQcObservation(@RequestBody Object qcObservationsDto);

    @GetMapping(value = "/qcObservationByProjectId/{projectId}")
    List<Object> getQcObservationsByProjectId(@PathVariable(value = "projectId") int projectId);

    @GetMapping(value = "/qcObservation/{id}")
    Object getQcObservationsById(@PathVariable(value = "id") long id);

    // Report controller

    @GetMapping("/reports/downloadSAPdf")
    ResponseEntity<Object> getTablePdf(@RequestParam(name = "projectId", required = false, defaultValue = "0") int projectId, @RequestParam(name = "locLevelId", required = false, defaultValue = "0") int locLevelId, @RequestParam(name = "locationId", required = false, defaultValue = "0") int locationId);

    @GetMapping("/reports/downloadSAObsPdf")
    ResponseEntity<Object> downloadSAObsPdf( @RequestParam(name = "locationId", required = false, defaultValue = "0") int locationId);

    @GetMapping("/reports/getSAActualWordReport")
    ResponseEntity<Object> getSAActualWordReport(@RequestParam(name = "projectId", required = false, defaultValue = "0") int projectId, @RequestParam(name = "locLevelId", required = false, defaultValue = "0") int locLevelId, @RequestParam(name = "locationId", required = false, defaultValue = "0") int locationId);

    @GetMapping("/reports/downloadSAObsWord")
    ResponseEntity<Object> downloadSAObsWord( @RequestParam(name = "locationId", required = true, defaultValue = "0") int locationId);

    @GetMapping("/reports/downloadReports")
    ResponseEntity<Object> downloadReports();

    @GetMapping("/reports/downloadSAAuditReportInWord")
    ResponseEntity<Object> downloadSAAuditReportInWord(@RequestParam(name = "projectId", required = true, defaultValue = "0") int projectId, @RequestParam(name = "date", required = true, defaultValue = "0") int date);

    @GetMapping("/reports/downloadBridgeAuditReport")
    ResponseEntity<Object> downloadBridgeAuditReport(@RequestParam(name = "projectId", required = true, defaultValue = "0") int projectId, @RequestParam(name = "locationId", required = true, defaultValue = "0") int locationId);

    @RequestMapping(value = "/reports/downloadSafetyObsInWord", method = RequestMethod.GET)
    ResponseEntity<Object> downloadSAObsProjectIdWord(@RequestParam(name = "projectId", required = true, defaultValue = "0") int projectId, @RequestParam(name = "fromDate", required = true) String fromDate, @RequestParam(name = "toDate", required = true) String toDate);

    @RequestMapping(value = "/reports/downloadImagesBridgeWise", method = RequestMethod.GET)
    ResponseEntity<Object> downloadImagesBridgeWise(@RequestParam(name = "projectId", required = true, defaultValue = "0") int projectId);

    @RequestMapping(value = "/reports/downloadQCObsInWord", method = RequestMethod.GET)
    ResponseEntity<Object> downloadQCObsInWord(@RequestParam(name = "projectId", required = true, defaultValue = "0") int projectId, @RequestParam(name = "fromDate", required = true) String fromDate, @RequestParam(name = "toDate", required = true) String toDate);

    // Safety Observation Controller

    @PostMapping(value = "/safetyObservation")
    Object saveSafetyObservation(@RequestBody Object safetyObservationsDto);

    @GetMapping(value = "/safetyObservationByProjectId/{projectId}")
    List<Object> getSafetyObservationsByProjectId(@PathVariable(value = "projectId") int projectId);

    @GetMapping(value = "/safetyObservation/{id}")
    Object getSafetyObservationsById(@PathVariable(value = "id") long id);

    // User controller

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    ResponseEntity<Object> createAuthenticationToken(@RequestBody Object authenticationRequest) throws Exception;

    @RequestMapping(value = "user/validateOtp", method = RequestMethod.POST)
    ResponseEntity<Object> validateOtp(@RequestBody Object otpRequest) throws Exception;
}
