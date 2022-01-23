package epsilongtmyon.app.common.db.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.OriginalStates;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.Table;

/**
 * アプリユーザー
 */
@Entity(metamodel = @Metamodel)
@Table(name = "app_user")
public class AppUser {

    /** ユーザーID */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "seq_app_user")
    @Column(name = "user_id")
    public Long userId;

    /** ユーザー別ID */
    @Column(name = "user_alias_id")
    public String userAliasId;

    /** ユーザー名 */
    @Column(name = "user_name")
    public String userName;

    /** メールアドレス */
    @Column(name = "mail_address")
    public String mailAddress;

    /** パスワード */
    @Column(name = "password")
    public String password;

    /** 管理者フラグ */
    @Column(name = "admin_flag")
    public Boolean adminFlag;

    /** */
    @OriginalStates
    AppUser originalStates;
}
