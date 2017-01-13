package jp.ddd.server.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.springframework.util.CollectionUtils;

public class PsLists {

  public static <T> ImmutableList<T> toImt(List<T> list) {
    return Lists.immutable.ofAll(list);
  }

  public static <T> MutableList<T> toMt(List<T> list) {
    return isEmpty(list) ? Lists.mutable.empty() : Lists.mutable.ofAll(list);
  }

  public static <T> ImmutableList<T> toImt(ImmutableList<Optional<T>> objOptList) {

    return objOptList.flatCollect(objOpt -> {
      if (objOpt.isPresent()) {
        return Lists.immutable.of(objOpt.get());
      } else {
        return Lists.immutable.empty();
      }
    });
  }

  /**
   * MutableListを作成する際はnullチェックが必要なので注意。
   */
  public static <T> List<T> toList(MutableList<T> mtList) {

    return mtList.stream().collect(Collectors.toList());
  }

  public static <T> List<T> toList(ImmutableList<T> imtList) {
    return imtList.toList().stream().collect(Collectors.toList());
  }

  public static boolean isEmpty(Collection<?> collection) {
    return CollectionUtils.isEmpty(collection);
  }

  public static boolean isNotEmpty(Collection<?> collection) {
    return !CollectionUtils.isEmpty(collection);
  }

  public static boolean isEmpty(Map<?, ?> map) {
    return CollectionUtils.isEmpty(map);
  }

  public static boolean isNotEmpty(Map<?, ?> map) {
    return !CollectionUtils.isEmpty(map);
  }

  public static <T> Stream<T> toStm(Stream<Optional<T>> objOptStm) {
    return objOptStm.flatMap(objOpt -> {
      if (objOpt.isPresent()) {
        return Stream.of(objOpt.get());
      } else {
        return Stream.empty();
      }
    });
  }

  public static <T> Stream<T> toStm(Optional<Stream<T>> objStmOpt) {
    return objStmOpt.orElse(Stream.empty());
  }
}
