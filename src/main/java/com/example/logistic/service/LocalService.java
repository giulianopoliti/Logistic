package com.example.logistic.service;

import com.example.logistic.model.roles.Local;
import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class LocalService {
    @Autowired
    private LocalRepository localRepository;
    public Local findByUUID (UUID uuid) {
        return localRepository.getReferenceById(uuid);
    }
    public Local createLocal (Map<String, Object> dataLocal) {
        Local local = new Local();
        Map<String, Object> ubicacionData = (Map<String, Object>) dataLocal.get("ubicacionEntrega");

        // Verificar si ubicacionData no es nulo antes de obtener los valores
        if (ubicacionData != null) {
            // Extraer latitud y longitud
            Double latitud = (Double) ubicacionData.get("latitud");
            Double longitud = (Double) ubicacionData.get("longitud");
            Ubicacion ubicacion = new Ubicacion(latitud,longitud);
            local.setUbicacion(ubicacion);
        }
        local.setHorarioApertura((java.sql.Time) dataLocal.get("horarioApertura"));
        local.setHorarioCierre((java.sql.Time) dataLocal.get("horarioCierre"));
        localRepository.save(local);
        return local;
}
}
