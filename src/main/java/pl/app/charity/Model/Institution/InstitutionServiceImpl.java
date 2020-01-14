package pl.app.charity.Model.Institution;

import org.springframework.stereotype.Service;
import pl.app.charity.Model.Donation.DonationRepository;

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
    public List<InstitutionEntity> findAllNotArchivized(){
        return institutionRepository.findNotArchivized();
    }
    public Long countInstitutions(){
        return institutionRepository.countInstitutions().orElse(0L);
    }
    public InstitutionEntity findById(Long id) { return institutionRepository.findById(id).orElse(null);}
    public void remove(InstitutionEntity institutionEntity){
        donationRepository.archivise(institutionEntity.getId());
        institutionRepository.archivise(institutionEntity.getId());
    }
}
