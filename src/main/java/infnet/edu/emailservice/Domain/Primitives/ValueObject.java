package infnet.edu.emailservice.Domain.Primitives;

import java.util.List;


public abstract class ValueObject {
    
    public abstract List<Object> getAtomicValues();

    public boolean equals(ValueObject other) {
        return other != null && valuesAreEqual(other);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ValueObject other && valuesAreEqual(other);
    }

    private boolean valuesAreEqual(ValueObject other) {
        return getAtomicValues().equals(other.getAtomicValues());
    }
}

