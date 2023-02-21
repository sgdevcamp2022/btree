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

class UserCategory(Base):
    __tablename__ = "usercategory"

    usercategoryId = Column(Integer, primary_key=True, unique=True, autoincrement=False)
    digitals = Column(Boolean, default=True)
    appliances = Column(Boolean, default=True)
    funitures = Column(Boolean, default=True)
    livings = Column(Boolean, default=True)
    kids = Column(Boolean, default=True)
    kid_books = Column(Boolean, default=True)
    woman_clothes = Column(Boolean, default=True)
    woman_things = Column(Boolean, default=True)
    man_things = Column(Boolean, default=True)
    beauty = Column(Boolean, default=True)
    sports = Column(Boolean, default=True)
    hobby_game_recodes = Column(Boolean, default=True)
    books = Column(Boolean, default=True)
    tickets = Column(Boolean, default=True)
    processed_food = Column(Boolean, default=True)
    pets = Column(Boolean, default=True)
    plants = Column(Boolean, default=True)
    others = Column(Boolean, default=True)
    buys = Column(Boolean, default=True)
class Item(Base):
    __tablename__ = "items"

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String(20), index=True)
    description = Column(String(20), index=True)
    owner_id = Column(Integer, ForeignKey("users.userId"))

    owner = relationship("User", back_populates="items")