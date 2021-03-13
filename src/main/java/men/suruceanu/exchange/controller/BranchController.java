package men.suruceanu.exchange.controller;

import men.suruceanu.exchange.dao.Branch;
import men.suruceanu.exchange.exception.BranchNotFoundException;
import men.suruceanu.exchange.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/branches")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public List<Branch> getAllBranches() {
        return branchService.getAllBranch();
    }

    @PostMapping("/branch")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Branch createBranch(@Valid @RequestBody Branch branch) {
        return branchService.addBranchInfo(branch);
    }

    @PutMapping("/branch/{branchId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Branch updateBranch(@PathVariable long branchId, @Valid @RequestBody Branch branch) throws BranchNotFoundException {
        return branchService.editBranchInfo(branchId, branch);
    }

    @DeleteMapping("/branch/{branchId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public void updateBranch(@PathVariable long branchId) {
        branchService.deleteBranch(branchId);
    }
}
