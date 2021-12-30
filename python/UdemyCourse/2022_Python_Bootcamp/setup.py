from setuptools import setup, find_packages
import pathlib

here = pathlib.Path(__file__).parent.resolve()

long_description = (here / 'README.md').read_text(encoding='utf-8')

setup(
    name='basics',
    version='1.0.0',
    description='A Python project which contains all my learnings',
    long_description=long_description,
    author='Pradyot Prakash',
    author_email='pradyotprksh4@gmail.com',
    keywords='basics, ds, algorithms',
    data_files=[(
        'data', ['data/']
    )],
    packages=find_packages(
        include=[
            'basics', 'src', 'basics.objects_ds'
        ]
    ),
    install_requires=[
        'loguru'
    ],
    entry_points={
        'console_scripts': [
            'basics = basics:main'
        ]
    }
)
