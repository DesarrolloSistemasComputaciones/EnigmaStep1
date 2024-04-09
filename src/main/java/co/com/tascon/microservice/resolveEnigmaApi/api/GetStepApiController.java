package co.com.tascon.microservice.resolveEnigmaApi.api;

import co.com.tascon.microservice.resolveEnigmaApi.model.ErrorDetail;
import co.com.tascon.microservice.resolveEnigmaApi.model.GetEnigmaRequest;
import co.com.tascon.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.tascon.microservice.resolveEnigmaApi.model.Header;
import co.com.tascon.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.tascon.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.tascon.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-03-05T21:04:17.162782-05:00[America/Bogota]")
@Controller
public class GetStepApiController implements GetStepApi {
    	
	@org.springframework.beans.factory.annotation.Autowired
    public GetStepApiController() {
    }

    public ResponseEntity<?> getStep(@ApiParam(value = "request body get enigma step", required = true) @Valid @RequestBody JsonApiBodyRequest body) {
        boolean isStepOne = (body.getData().get(0).getStep().equalsIgnoreCase("1"));
    	
    	if (!isStepOne) {        	
        	return new ResponseEntity<>(responseError(body), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(responseSuccess(body), HttpStatus.OK);
    }
    
    public ResponseEntity<String> getStepOne() {
    	return new ResponseEntity<>("Step 1: Open the refrigerator", HttpStatus.OK);
    }
    
    private List<JsonApiBodyResponseErrors> responseError(JsonApiBodyRequest body) {
    	ErrorDetail errorDetail = new ErrorDetail();
    	errorDetail.setCode("001");
    	errorDetail.setDetail("Step: ".concat(body.getData().get(0).getStep()).concat(" not supported - Expected: 1"));
    	errorDetail.setId(body.getData().get(0).getHeader().getId());
    	errorDetail.setSource("/getStep");
    	errorDetail.setStatus("400");
    	errorDetail.setTitle("Step not supported");
    	
    	JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
    	responseError.addErrorsItem(errorDetail);
    	
    	List<JsonApiBodyResponseErrors> responseErrorsList = new ArrayList<JsonApiBodyResponseErrors>(); 
    	responseErrorsList.add(responseError);
    	
    	return responseErrorsList;
    }
    
    private List<JsonApiBodyResponseSuccess> responseSuccess(JsonApiBodyRequest body) {
        GetEnigmaStepResponse responseEnigma = new GetEnigmaStepResponse();    
        responseEnigma.setHeader(body.getData().get(0).getHeader());
        responseEnigma.setStep(body.getData().get(0).getStep());
        responseEnigma.setStepDescription("Open the refrigerator");
        
        JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
        responseSuccess.addDataItem(responseEnigma);
        
        List<JsonApiBodyResponseSuccess> responseSuccessList = new ArrayList<JsonApiBodyResponseSuccess>();  
        responseSuccessList.add(responseSuccess);
        
        return responseSuccessList;
    }

}
