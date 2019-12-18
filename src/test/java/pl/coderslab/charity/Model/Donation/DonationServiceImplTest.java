package pl.coderslab.charity.Model.Donation;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderslab.charity.Model.UserEntity.UserServiceImp;


@RunWith(MockitoJUnitRunner.class)
public class DonationServiceImplTest {

    @Mock
    UserServiceImp userServiceImp;
    @Mock
    DonationRepository donationRepository;
    @InjectMocks
    private DonationServiceImpl donationService;

    private DonationEntity donation;

    @Before
    public void beforeEachTest(){
        donation = new DonationEntity();
    }

    @Test
    public void newDonation() {
        donationService.newDonation(donation);

        Assert.assertFalse(donation.getDelivered());
        Assert.assertFalse(donation.getArchivised());
        Assert.assertNotNull(donation.getCreationTime());
    }

    @Test
    public void flipDelivered() {


        donation.setDelivered(true);
        Boolean before = donation.getDelivered();
        donationService.flipDelivered(donation);
        Boolean after = donation.getDelivered();
        Assert.assertNotEquals(before,after);


        before = donation.getDelivered();
        donationService.flipDelivered(donation);
        after = donation.getDelivered();
        Assert.assertNotEquals(before,after);

    }

}
