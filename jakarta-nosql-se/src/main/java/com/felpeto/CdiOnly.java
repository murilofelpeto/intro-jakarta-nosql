package com.felpeto;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.jnosql.communication.document.Document;
import org.eclipse.jnosql.communication.document.DocumentCondition;
import org.eclipse.jnosql.communication.document.DocumentDeleteQuery;
import org.eclipse.jnosql.communication.document.DocumentEntity;
import org.eclipse.jnosql.communication.document.DocumentManager;
import org.eclipse.jnosql.communication.document.DocumentQuery;

public class CdiOnly {

  public static void main(String[] args) {
    try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

      DocumentManager manager = container.select(DocumentManager.class).get();

      manager.delete(DocumentDeleteQuery.delete()
          .from("developer")
          .build());

      var entity = DocumentEntity.of("developer",
          List.of(Document.of("_id", "1"),
              Document.of("name", "Murilo"),
              Document.of("age", 29),
              Document.of("birthday", LocalDate.of(1993, 11, 18))
          ));

      manager.insert(entity);

      var data = DocumentQuery.builder()
          .from("developer")
          .select()
          .where(DocumentCondition.eq(Document.of("_id", "1")))
          .getResult(manager);

      data.forEach(doc -> {

        final Map<String, Object> row = new HashMap<>(doc.toMap());

        doc.find("_id", Long.class).ifPresent(v -> row.put("_id", v));
        doc.find("birthday", LocalDate.class).ifPresent(v -> row.put("birthday", v));

        System.out.println(row);

      });

    }
  }
}
