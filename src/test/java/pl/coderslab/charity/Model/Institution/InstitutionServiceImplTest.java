package pl.coderslab.charity.Model.Institution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderslab.charity.Model.Donation.DonationEntity;
import pl.coderslab.charity.Model.Donation.DonationRepository;
import pl.coderslab.charity.Model.Donation.DonationServiceImpl;
import pl.coderslab.charity.Model.UserEntity.UserServiceImp;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class InstitutionServiceImplTest {

    @Mock
    InstitutionRepository institutionRepository;
    @Mock
    DonationRepository donationRepository;
    @InjectMocks
    private InstitutionServiceImpl institutionService;



    @Test
    public void remove() {
        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setArchivised(false);
        institutionService.remove(institutionEntity);
        Assert.assertFalse(institutionEntity.getArchivised());

    }
}