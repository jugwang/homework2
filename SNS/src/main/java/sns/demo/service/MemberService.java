package sns.demo.service;

import org.springframework.stereotype.Service;
import sns.demo.domain.Member;
import sns.demo.repository.MemberRepository;

@Service
public class MemberService {

    private MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public boolean createMember(String id, String name, String password) {

        Member tempMember = repository.readMember(id);
        //tempMember 값이 있다면(null 이 아니라면)
        if (tempMember != null) {
            throw new RuntimeException("이미 가입한 id 입니다");
        }
        //id or password 가 눌 이라면
        if (id == null || password == null) {
            throw new RuntimeException("id 와 password 값은 필수입니다");
        }

        Member newMember = new Member();
        newMember.setId(id);
        newMember.setName(name);
        newMember.setPassword(password);
        newMember.setPassFailCount(0);
        return repository.createMember(newMember);
    }

    public boolean removeMember(String id) {
        Member targetMember = repository.readMember(id);
        if (targetMember == null) {
            System.out.println("삭제하려 했으나 찾지못했습니다");
            return false;
        }
        return repository.deleteMember(targetMember);
    }
    public boolean login(String id, String password) {

        Member member = repository.readMember(id);
        if (member.getPassword().equals(password)) {
            return true;
        }
        if (member.getPassFailCount() >= 5) {
            removeMember(id);
            throw new RuntimeException(member.getPassFailCount() + " 번 틀려서 계정이 삭제됩니다");
        }else{
            member.setPassFailCount(member.getPassFailCount() + 1);
            throw new RuntimeException(member.getPassFailCount()+ "번 틀렸습니다");
        }
    }

    public boolean update(String id, String newpassword, String name) {

        Member targetMember = repository.readMember(id);
        if (targetMember == null) {
            System.out.println("수정하려고 했으나 찾지 못했습니다");
            return false;
        }
        if (newpassword != null) {
            if (newpassword.equals(targetMember.getPassword())) {
                throw new RuntimeException("현재 패스워드와 같습니다");
            }
            targetMember.setPassword(newpassword);
        }
        if (name != null) {
            targetMember.setName(name);
        }
        return true;
    }

}

