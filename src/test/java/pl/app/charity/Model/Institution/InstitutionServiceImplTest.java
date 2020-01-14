package pl.app.charity.Model.Institution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.app.charity.Model.Donation.DonationRepository;

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