package com.vn.ntt.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBuddy is a Querydsl query type for Buddy
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBuddy extends EntityPathBase<Buddy> {

    private static final long serialVersionUID = 1032204281L;

    public static final QBuddy buddy = new QBuddy("buddy");

    public final QModel _super = new QModel(this);

    public final ListPath<Hash, QHash> hashList = this.<Hash, QHash>createList("hashList", Hash.class, QHash.class, PathInits.DIRECT2);

    //inherited
    public final StringPath id = _super.id;

    public final DateTimePath<java.util.Date> lastUpTime = createDateTime("lastUpTime", java.util.Date.class);

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final NumberPath<Double> lon = createNumber("lon", Double.class);

    public final StringPath name = createString("name");

    public final StringPath token = createString("token");

    public QBuddy(String variable) {
        super(Buddy.class, forVariable(variable));
    }

    public QBuddy(Path<? extends Buddy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBuddy(PathMetadata<?> metadata) {
        super(Buddy.class, metadata);
    }

}

