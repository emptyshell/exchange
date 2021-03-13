package men.suruceanu.exchange.service;

import men.suruceanu.exchange.dao.Branch;
import men.suruceanu.exchange.exception.BranchNotFoundException;
import men.suruceanu.exchange.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public Branch addBranchInfo(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch editBranchInfo(Long branchId, Branch branch) throws BranchNotFoundException {
        Branch existingBranch = branchRepository.findById(branchId).orElseThrow(() -> new BranchNotFoundException("These branch is not in the database!"));

        existingBranch.setBranchCity(branch.getBranchCity());
        existingBranch.setBranchAddress(branch.getBranchAddress());
        existingBranch.setBranchCountry(branch.getBranchCountry());
        existingBranch.setBranchZip(branch.getBranchZip());
        existingBranch.setBranchLocalCurrency(branch.getBranchLocalCurrency());

        return branchRepository.save(existingBranch);
    }

    public void deleteBranch(Long branchId) {
        branchRepository.findById(branchId).ifPresent(branch -> branchRepository.delete(branch));
    }

    public List<Branch> getAllBranch() {
        return branchRepository.findAll();
    }

}
