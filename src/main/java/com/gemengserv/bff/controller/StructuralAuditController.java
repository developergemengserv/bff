package com.gemengserv.bff.controller;

import com.gemengserv.bff.service.StructuralAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/structuralaudit")
public class StructuralAuditController
{
    @Autowired
    StructuralAuditService structuralAuditService;

    // File Upload Controller

    @PostMapping(value = "/upload")
    public Object uploadFile(@RequestParam("file") MultipartFile file,
                      @RequestParam("obsId") int obsId,
                      @RequestParam("mediaType") String  mediaType)
    {
        return structuralAuditService.uploadFile(file, obsId, mediaType);
    }

    // Location Master Controller

    @GetMapping("/locations/{projectId}")
    public List<Object> getLocations(@PathVariable(value = "projectId") int projectId)
    {
        return structuralAuditService.getLocations(projectId);
    }

    @GetMapping("/locations/relatedTo/{relatedToId}")
    public List<Object> getLocationsByRelatedTo(@PathVariable(value = "relatedToId") int relatedToId)
    {
        return structuralAuditService.getLocationsByRelatedTo(relatedToId);
    }

    // Master controller

    @GetMapping(value = "/masters")
    public Object getAllMasters()
    {
        return structuralAuditService.getAllMasters();
    }

    // Observation controller

    @PostMapping(value = "/observation")
    Object saveObservation(@RequestBody Object observationDto)
    {
        return structuralAuditService.saveObservation(observationDto);
    }

    @GetMapping(value = "/observation")
    List<Object> getObservations()
    {
        return structuralAuditService.getObservations();
    }

    @GetMapping(value = "/observation/{id}")
    public Object getObservationById(@PathVariable(value = "id") long id)
    {
        return structuralAuditService.getObservationById(id);
    }

    @GetMapping("/observationsByLocation/{level1Id}")
    public List<Object> getObservationByLevel1Id(@PathVariable(value = "level1Id") int level1Id)
    {
        return structuralAuditService.getObservationByLevel1Id(level1Id);
    }

    @GetMapping("/observationsByProjectId/{projectId}")
    public List<Object> getObservationByProjectId(@PathVariable(value = "projectId") int projectId)
    {
        return structuralAuditService.getObservationByProjectId(projectId);
    }

    @GetMapping("/observationsByLocationId/{locationId}")
    public List<Object> getObservationByLocationId(@PathVariable(value = "locationId") int locationId)
    {
        return structuralAuditService.getObservationByLocationId(locationId);
    }

    // Qc Observation Controller

    @PostMapping(value = "/qcObservation")
    public Object saveQcObservation(@RequestBody Object qcObservationsDto)
    {
        return structuralAuditService.saveQcObservation(qcObservationsDto);
    }

    @GetMapping(value = "/qcObservationByProjectId/{projectId}")
    public List<Object> getQcObservationsByProjectId(@PathVariable(value = "projectId") int projectId)
    {
        return structuralAuditService.getQcObservationsByProjectId(projectId);
    }

    @GetMapping(value = "/qcObservation/{id}")
    public Object getQcObservationsById(@PathVariable(value = "id") long id)
    {
        return structuralAuditService.getQcObservationsById(id);
    }

    // Report controller

    @GetMapping("/reports/downloadSAPdf")
    public ResponseEntity<Object> getTablePdf(@RequestParam(name = "projectId", required = false, defaultValue = "0") int projectId, @RequestParam(name = "locLevelId", required = false, defaultValue = "0") int locLevelId, @RequestParam(name = "locationId", required = false, defaultValue = "0") int locationId)
    {
        return structuralAuditService.getTablePdf(projectId, locLevelId, locationId);
    }

    @GetMapping("/reports/downloadSAObsPdf")
    public ResponseEntity<Object> downloadSAObsPdf( @RequestParam(name = "locationId", required = false, defaultValue = "0") int locationId)
    {
        return structuralAuditService.downloadSAObsPdf(locationId);
    }

    @GetMapping("/reports/getSAActualWordReport")
    public ResponseEntity<Object> getSAActualWordReport(@RequestParam(name = "projectId", required = false, defaultValue = "0") int projectId, @RequestParam(name = "locLevelId", required = false, defaultValue = "0") int locLevelId, @RequestParam(name = "locationId", required = false, defaultValue = "0") int locationId)
    {
        return structuralAuditService.getSAActualWordReport(projectId, locLevelId, locationId);
    }

    @GetMapping("/reports/downloadSAObsWord")
    public ResponseEntity<Object> downloadSAObsWord( @RequestParam(name = "locationId", required = true, defaultValue = "0") int locationId)
    {
        return structuralAuditService.downloadSAObsWord(locationId);
    }

    @GetMapping("/reports/downloadReports")
    public ResponseEntity<Object> downloadReports()
    {
        return structuralAuditService.downloadReports();
    }

    @GetMapping("/reports/downloadSAAuditReportInWord")
    public ResponseEntity<Object> downloadSAAuditReportInWord(@RequestParam(name = "projectId", required = true, defaultValue = "0") int projectId, @RequestParam(name = "date", required = true, defaultValue = "0") int date)
    {
        return structuralAuditService.downloadSAAuditReportInWord(projectId, date);
    }

    @GetMapping("/reports/downloadBridgeAuditReport")
    public ResponseEntity<Object> downloadBridgeAuditReport(@RequestParam(name = "projectId", required = true, defaultValue = "0") int projectId, @RequestParam(name = "locationId", required = true, defaultValue = "0") int locationId)
    {
        return structuralAuditService.downloadBridgeAuditReport(projectId, locationId);
    }

    @RequestMapping(value = "/reports/downloadSafetyObsInWord", method = RequestMethod.GET)
    public ResponseEntity<Object> downloadSAObsProjectIdWord(@RequestParam(name = "projectId", required = true, defaultValue = "0") int projectId, @RequestParam(name = "fromDate", required = true) String fromDate, @RequestParam(name = "toDate", required = true) String toDate)
    {
        return structuralAuditService.downloadSAObsProjectIdWord(projectId, fromDate, toDate);
    }

    @RequestMapping(value = "/reports/downloadImagesBridgeWise", method = RequestMethod.GET)
    public ResponseEntity<Object> downloadImagesBridgeWise(@RequestParam(name = "projectId", required = true, defaultValue = "0") int projectId)
    {
        return structuralAuditService.downloadImagesBridgeWise(projectId);
    }

    @RequestMapping(value = "/reports/downloadQCObsInWord", method = RequestMethod.GET)
    public ResponseEntity<Object> downloadQCObsInWord(@RequestParam(name = "projectId", required = true, defaultValue = "0") int projectId, @RequestParam(name = "fromDate", required = true) String fromDate, @RequestParam(name = "toDate", required = true) String toDate)
    {
        return structuralAuditService.downloadQCObsInWord(projectId, fromDate, toDate);
    }

    // Safety Observation Controller

    @PostMapping(value = "/safetyObservation")
    public Object saveSafetyObservation(@RequestBody Object safetyObservationsDto)
    {
        return structuralAuditService.saveSafetyObservation(safetyObservationsDto);
    }

    @GetMapping(value = "/safetyObservationByProjectId/{projectId}")
    public List<Object> getSafetyObservationsByProjectId(@PathVariable(value = "projectId") int projectId)
    {
        return structuralAuditService.getSafetyObservationsByProjectId(projectId);
    }

    @GetMapping(value = "/safetyObservation/{id}")
    public Object getSafetyObservationsById(@PathVariable(value = "id") long id)
    {
        return structuralAuditService.getSafetyObservationsById(id);
    }

    // User controller

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody Object authenticationRequest) throws Exception
    {
        return structuralAuditService.createAuthenticationToken(authenticationRequest);
    }

    @RequestMapping(value = "user/validateOtp", method = RequestMethod.POST)
    public ResponseEntity<Object> validateOtp(@RequestBody Object otpRequest) throws Exception
    {
        return structuralAuditService.validateOtp(otpRequest);
    }
}
