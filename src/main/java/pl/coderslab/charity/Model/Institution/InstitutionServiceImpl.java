package pl.coderslab.charity.Model.Institution;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.Model.Donation.DonationRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class InstitutionServiceImpl {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    public List<InstitutionEntity> findAll(){
        return institutionRepository.findAll();
    }

    public Long countInstitutions(){
        return institutionRepository.countInstitutions().orElse(0L);
    }

    public void remove(Long id){
        donationRepository.archivise(id);
        institutionRepository.archivise(id);
    }
}
