"""Setup file"""
import pathlib
from setuptools import setup, find_packages

here = pathlib.Path(__file__).parent.resolve()

long_description = (here / 'README.md').read_text(encoding='utf-8')

setup(
    name='ds_algo',
    version='1.0.0',
    description='A set of programs to learn about different types of DS & Algo',
    long_description=long_description,
    author='Pradyot Prakash',
    author_email='pradyotprksh4@gmail.com',
    keywords='basics, ds, algo, practice, interview, questions',
    data_files=[(
        'data', []
    )],
    packages=find_packages(
        include=[
            'src', 'practice', 'interview_preperations', 'ds', 'algo'
        ]
    ),
    install_requires=[],
    entry_points={
        'console_scripts': [
            'ds_algo = ds_algo:main'
        ]
    }
)
