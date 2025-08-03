package com.example.uks.unit.controllers;

import com.example.uks.controllers.OrganisationController;
import com.example.uks.dto.organisation.AddMemberDTO;
import com.example.uks.dto.organisation.OrganisationCreateDTO;
import com.example.uks.dto.organisation.OrganisationUpdateDTO;
import com.example.uks.dto.user.MemberDTO;
import com.example.uks.exceptions.AccessDeniedException;
import com.example.uks.exceptions.AttributeNotUniqueException;
import com.example.uks.exceptions.OrganisationNotFound;
import com.example.uks.exceptions.UserNotFound;
import com.example.uks.model.Organisation;
import com.example.uks.model.User;
import com.example.uks.services.OrganisationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrganisationControllerTest {

    @InjectMocks
    private OrganisationController organisationController;

    @Mock
    private OrganisationService organisationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateOrganisation_success() {
        OrganisationUpdateDTO dto = new OrganisationUpdateDTO();
        Organisation organisation = new Organisation();
        organisation.setId(1);
        organisation.setName("Updated Organisation");
        organisation.setOwner(new User());

        when(organisationService.updateOrganisation(1, dto)).thenReturn(organisation);

        ResponseEntity<Map<String, Object>> response = organisationController.updateOrganisation(1, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().get("data"));
    }

    @Test
    void updateOrganisation_notFound(){
        OrganisationUpdateDTO dto = new OrganisationUpdateDTO();

        when(organisationService.updateOrganisation(1, dto)).thenThrow(new OrganisationNotFound("Organisation not found"));

        ResponseEntity<Map<String, Object>> response = organisationController.updateOrganisation(1, dto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Organisation not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void deactivateOrganisation_success() {
        ResponseEntity<Void> response = organisationController.deactivateOrganisation(1, 10);

        verify(organisationService).deactivateOrganisation(1, 10);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deactivateOrganisation_notFound() {

        doThrow(new OrganisationNotFound("Organisation not found")).when(organisationService).deactivateOrganisation(1, 10);

        ResponseEntity<Void> response = organisationController.deactivateOrganisation(1, 10);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createOrganisation_success() {
        OrganisationCreateDTO dto = new OrganisationCreateDTO();
        Organisation organisation = new Organisation();
        organisation.setId(1);
        organisation.setName("Org");
        organisation.setOwner(new User());

        when(organisationService.createOrganisation(dto)).thenReturn(organisation);

        ResponseEntity<Map<String, Object>> response = organisationController.createOrganisation(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().get("data"));
    }

    @Test
    void createOrganisation_attributeNotUnique() {
        OrganisationCreateDTO dto = new OrganisationCreateDTO();

        when(organisationService.createOrganisation(dto)).thenThrow(new AttributeNotUniqueException("Attribute not unique"));

        ResponseEntity<Map<String, Object>> response = organisationController.createOrganisation(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Attribute not unique", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void createOrganisation_userNotFound() {
        OrganisationCreateDTO dto = new OrganisationCreateDTO();

        when(organisationService.createOrganisation(dto)).thenThrow(new UserNotFound("User not found"));

        ResponseEntity<Map<String, Object>> response = organisationController.createOrganisation(dto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void getUserOrganisations_success() {
        Organisation organisation = new Organisation();
        organisation.setId(1);
        organisation.setName("Org");
        organisation.setOwner(new User());

        when(organisationService.getUserOrganisations(100)).thenReturn(List.of(organisation));

        ResponseEntity<Map<String, Object>> response = organisationController.getUserOrganisations(100);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().get("data") instanceof List<?>);
    }

    @Test
    void getUserOrganisations_userNotFound() {

        when(organisationService.getUserOrganisations(100)).thenThrow(new UserNotFound("User not found"));

        ResponseEntity<Map<String, Object>> response = organisationController.getUserOrganisations(100);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void getOrganisationById_success() {
        Organisation organisation = new Organisation();
        organisation.setId(1);
        organisation.setName("Org");
        organisation.setOwner(new User());

        when(organisationService.getOrganisationById(1)).thenReturn(organisation);

        ResponseEntity<Map<String, Object>> response = organisationController.getOrganisationById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().get("data"));
    }

    @Test
    void getOrganisationById_notFound() {
        when(organisationService.getOrganisationById(1)).thenThrow(new OrganisationNotFound("Organisation not found"));

        ResponseEntity<Map<String, Object>> response = organisationController.getOrganisationById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Organisation not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void addMemberToOrganisation_success() {
        AddMemberDTO dto = new AddMemberDTO();
        dto.setOwnerId(1);
        dto.setMemberId(2);

        ResponseEntity<Map<String, Object>> response = organisationController.addMemberToOrganisation(10, dto);

        verify(organisationService).addMember(10, 1, 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User successfully added to organisation.", response.getBody().get("message"));
    }

    @Test
    void addMemberToOrganisation_organisationNotFound() {
        AddMemberDTO dto = new AddMemberDTO();
        dto.setOwnerId(1);
        dto.setMemberId(2);

        doThrow(new OrganisationNotFound("Organisation not found")).when(organisationService).addMember(10, 1, 2);

        ResponseEntity<Map<String, Object>> response = organisationController.addMemberToOrganisation(10, dto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Organisation not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void addMemberToOrganisation_userNotFound() {
        AddMemberDTO dto = new AddMemberDTO();
        dto.setOwnerId(1);
        dto.setMemberId(2);

        doThrow(new UserNotFound("User not found")).when(organisationService).addMember(10, 1, 2);

        ResponseEntity<Map<String, Object>> response = organisationController.addMemberToOrganisation(10, dto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void addMemberToOrganisation_accessDenied() {
        AddMemberDTO dto = new AddMemberDTO();
        dto.setOwnerId(1);
        dto.setMemberId(2);

        doThrow(new AccessDeniedException("Only the owner can add members.")).when(organisationService).addMember(10, 1, 2);

        ResponseEntity<Map<String, Object>> response = organisationController.addMemberToOrganisation(10, dto);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Only the owner can add members.", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void testAddMemberToOrganisation_illegalArgument() {
        AddMemberDTO dto = new AddMemberDTO();
        dto.setOwnerId(1);
        dto.setMemberId(2);

        doThrow(new IllegalArgumentException("User already member"))
                .when(organisationService).addMember(10, 1, 2);

        ResponseEntity<Map<String, Object>> response = organisationController.addMemberToOrganisation(10, dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User already member", response.getBody().get("message"));
    }

    @Test
    void testAddMemberToOrganisation_unexpectedError() {
        AddMemberDTO dto = new AddMemberDTO();
        dto.setOwnerId(1);
        dto.setMemberId(2);

        doThrow(new RuntimeException("DB error")).when(organisationService).addMember(10, 1, 2);

        ResponseEntity<Map<String, Object>> response = organisationController.addMemberToOrganisation(10, dto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(((String) response.getBody().get("message")).contains("Unexpected server error"));
    }

    @Test
    void getOrganisationMembers_success() {
        List<MemberDTO> members = List.of(new MemberDTO());

        when(organisationService.getOrganisationMembers(1, 2)).thenReturn(members);

        ResponseEntity<Map<String, Object>> response = organisationController.getOrganisationMembers(1, 2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().get("data") instanceof List);
    }

    @Test
    void getOrganisationMembers_organisationNotFound() {
        when(organisationService.getOrganisationMembers(1, 2)).thenThrow(new OrganisationNotFound("Organisation not found"));

        ResponseEntity<Map<String, Object>> response = organisationController.getOrganisationMembers(1, 2);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Organisation not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void getOrganisationMembers_accessDenied() {

        when(organisationService.getOrganisationMembers(1, 2)).thenThrow(new AccessDeniedException("Access denied"));

        ResponseEntity<Map<String, Object>> response = organisationController.getOrganisationMembers(1, 2);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Access denied", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }
}