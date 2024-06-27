package br.com.pipocaagil.CorraAgil.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <ModelObject, ObjectDTO> ObjectDTO parserObject(ModelObject origin, Class<ObjectDTO> destination) {
        return mapper.map(origin, destination);
    }

    public static <ModelObject, ObjectDTO> List<ObjectDTO> parserListObject(List<ModelObject> origin, Class<ObjectDTO> destination) {
        List<ObjectDTO> destinationObjects = new ArrayList<ObjectDTO>();
        for (ModelObject modelObject : origin) {
            destinationObjects.add(mapper.map(modelObject, destination));
        }
        return destinationObjects;
    }
}
