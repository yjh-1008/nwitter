from re import U
from django.db import models
from django.contrib.auth.models import User

""" 일단 써본 것
class User_owner(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    id = models.AutoField(primary_key=True, verbose_name='Primary Key')
    number = models.CharField(max_length=13, verbose_name='전화번호')
    name = models.CharField(max_length=10,null=True, verbose_name='이름')
    # store 모델 만들면 추가
    #store_id = models.ForeignKey(store, on_delete=models.CASCADE) 

    # username, password는 기본 User의 Field 이용

class User_customer(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    id= models.AutoField(primary_key=True, verbose_name='Primary Key')
    number = models.CharField(max_length=13, verbose_name='전화번호')
    name = models.CharField(max_length=10,  verbose_name='이름')

    # username, password는 기본 User의 Field 이용
"""


class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    id= models.AutoField(primary_key=True, verbose_name='Primary Key')
    number = models.CharField(max_length=13, verbose_name='전화번호')
    name = models.CharField(max_length=10,  verbose_name='이름')
    # 0 == owner , 1 == customer
    belong = models.IntegerField()
    
    def __str__(self):
        return self.user.username

