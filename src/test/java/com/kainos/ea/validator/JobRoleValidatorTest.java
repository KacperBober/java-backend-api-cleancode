package com.kainos.ea.validator;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.service.BandService;
import com.kainos.ea.service.JobFamilyService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JobRoleValidatorTest {
    BandService bandService = Mockito.mock(BandService.class);
    JobFamilyService jobFamilyService = Mockito.mock(JobFamilyService.class);
    JobRoleValidator jobRoleValidator = new JobRoleValidator(jobFamilyService, bandService);

    @Test
    public void isValidJobRole_shouldReturnTrue_whenValidJobRole () throws DatabaseConnectionException, SQLException {
        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "Software engineer does something for sure",
                "https://www.google.com/",
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertTrue(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenNameMoreThan100Char () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                StringUtils.repeat("*", 101),
               "Software engineer does something for sure",
               "https://www.google.com/",
               5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobSpecUrlMoreThan1000Char () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "Software engineer does something for sure",
                StringUtils.repeat("*", 1001),
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobSpecMoreThan1000Char () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                StringUtils.repeat("*", 1001),
                "https://www.google.com/",
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }


    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobSpecNull () throws DatabaseConnectionException, SQLException {

       final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                null,
                "https://www.google.com/",
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobSpecBlank () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "",
                "https://www.google.com/",
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobSpecBlankWithSpace () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "    ",
                "https://www.google.com/",
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobSpecURLNull () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "Spec",
                null,
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobSpecURLBlank () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "Spec",
                "",
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobSpecURLBlankWithSpace () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "Spec",
                "    ",
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobNameNull () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                null,
                "Spec",
                "URL",
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobNameBlank () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "",
                "Spec",
                "URL",
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobNameBlankWithSpace () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "     ",
                "Spec",
                "URL",
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenBandIDNotPresent () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Name",
                "Spec",
                "URL",
                5,
                1);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenJobFamilyIDNotPresent () throws DatabaseConnectionException, SQLException {

        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Name",
                "Spec",
                "URL",
                1,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldReturnFalse_whenInvalidURL () throws DatabaseConnectionException, SQLException {
        final JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "Software engineer does something for sure",
                "htww.samanthaming.com/tidbits/94-how-to-check-if-object-is-empty/",
                5,
                3);

        Mockito.when(bandService.getBands()).thenReturn(Arrays.asList(new Band(3, 3, "band level")));
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(Arrays.asList(new JobFamily(5, "job family")));
        assertFalse(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }
}
