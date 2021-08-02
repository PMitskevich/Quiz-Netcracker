package com.example.repository;

import com.example.model.Game;
import org.dom4j.tree.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;
import com.querydsl.core.types.dsl.EntityPathBase;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoRepositoryBean
    public interface ExCustomRepository<T extends Game, P extends EntityPathBase<T>, ID extends Serializable>
        extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T>, QuerydslBinderCustomizer<P> {

    @Override
    default void customize(QuerydslBindings bindings, P root) {
    }
}
