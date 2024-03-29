# Generated by Django 4.0.6 on 2022-08-05 07:42

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('danceapp', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Course',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('title', models.CharField(max_length=200)),
                ('body', models.TextField()),
                ('uploadDate', models.DateTimeField(auto_now_add=True)),
                ('updateDate', models.DateTimeField(auto_now=True)),
                ('photo', models.ImageField(blank=True, null=True, upload_to='thumbnail')),
                ('video', models.FileField(blank=True, null=True, upload_to='video')),
                ('likes_count', models.PositiveIntegerField(default=0)),
                ('register_count', models.PositiveIntegerField(default=0)),
                ('maxRegCount', models.IntegerField(default=0)),
                ('startDate', models.DateField()),
                ('genreName', models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to='danceapp.genre')),
                ('likes_user', models.ManyToManyField(blank=True, related_name='likes_course', to=settings.AUTH_USER_MODEL)),
                ('register_user', models.ManyToManyField(blank=True, related_name='register_user', to=settings.AUTH_USER_MODEL)),
                ('userId', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
    ]
