package sns.demo.repository;

import org.springframework.stereotype.Repository;
import sns.demo.domain.Member;

import java.util.ArrayList;
import java.util.List;


@Repository
public class MemberRepository {
//    1.회원가입하기
//    2.중복가입 안됌
//    3.5번이상 틀리면 로그인 불가
//    4.패스워드 바꾸기 기능 추가
//    5.직전 패스워드 사용 못함
//    create read update delete crud

    private List<Member> members = new ArrayList<>();

    public boolean createMember(Member member){
        members.add(member);
        return true;
    }
    public boolean deleteMember(Member member){
        members.remove(member);
        return true;
    }
    public Member readMember(String id){
        for( Member dummy : members) {
            if (dummy.getId().equals(id)) {
                return dummy;
            }
        }
        return null;
    }
}
