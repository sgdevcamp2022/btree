from sqlalchemy import Boolean, Column, ForeignKey, Integer, String, Float, DateTime
from sqlalchemy.orm import relationship

from database import Base
import datetime

class User(Base):
    __tablename__ = "users"

    userId = Column(Integer, primary_key=True, index=True)
    email = Column(String(50), unique=True, index=True)
    nickname = Column(String(50), nullable = True)
    hashed_password = Column(String(100))
    manner_temporature = Column(Float(precision=2, asdecimal=True), default=36.5)
    is_active = Column(Boolean, default=False)
    create_at = Column(DateTime, default=datetime.datetime.utcnow)
    gps_auth = Column(Boolean, default=False)
    locate = Column(String(100), nullable = True)

    items = relationship("Item", back_populates="owner")#

class Item(Base):
    __tablename__ = "items"

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String(20), index=True)
    description = Column(String(20), index=True)
    owner_id = Column(Integer, ForeignKey("users.userId"))

    owner = relationship("User", back_populates="items")