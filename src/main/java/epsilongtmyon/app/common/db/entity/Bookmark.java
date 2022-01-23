package epsilongtmyon.app.common.db.entity;

import java.time.LocalDateTime;

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
 * ブックマーク
 */
@Entity(metamodel = @Metamodel)
@Table(name = "bookmark")
public class Bookmark {

    /** ブックマークID */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "seq_bookmark")
    @Column(name = "bookmark_id")
    public Long bookmarkId;

    /** ユーザーID */
    @Column(name = "user_id")
    public Long userId;

    /** ユーザー表示順 */
    @Column(name = "user_list_order")
    public Integer userListOrder;

    /** タイトル */
    @Column(name = "title")
    public String title;

    /** URL */
    @Column(name = "url")
    public String url;

    /** 説明 */
    @Column(name = "description")
    public String description;

    /** 共有フラグ */
    @Column(name = "shared_flag")
    public Boolean sharedFlag;

    /** 作成日時 */
    @Column(name = "created_date")
    public LocalDateTime createdDate;

    /** */
    @OriginalStates
    Bookmark originalStates;
}
