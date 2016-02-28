package org.spring.mongdb.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QModel is a Querydsl query type for Model
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QModel extends BeanPath<Model> {

    private static final long serialVersionUID = 1042184284L;

    public static final QModel model = new QModel("model");

    public final StringPath id = createString("id");

    public QModel(String variable) {
        super(Model.class, forVariable(variable));
    }

    public QModel(Path<? extends Model> path) {
        super(path.getType(), path.getMetadata());
    }

    public QModel(PathMetadata<?> metadata) {
        super(Model.class, metadata);
    }

}

