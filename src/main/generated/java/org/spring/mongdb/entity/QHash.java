package org.spring.mongdb.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QHash is a Querydsl query type for Hash
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHash extends EntityPathBase<Hash> {

    private static final long serialVersionUID = -382185093L;

    public static final QHash hash = new QHash("hash");

    public final QModel _super = new QModel(this);

    //inherited
    public final StringPath id = _super.id;

    public final StringPath tag = createString("tag");

    public final StringPath type = createString("type");

    public QHash(String variable) {
        super(Hash.class, forVariable(variable));
    }

    public QHash(Path<? extends Hash> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHash(PathMetadata<?> metadata) {
        super(Hash.class, metadata);
    }

}

