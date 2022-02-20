from setuptools import setup, find_packages
import pathlib

here = pathlib.Path(__file__).parent.resolve()

long_description = (here / 'README.md').read_text(encoding='utf-8')

setup(
    name='pyblog',
    version='1.0.0',
    description='A blog application made using python and firebase as a backend service.',
    long_description=long_description,
    author='Pradyot Prakash',
    author_email='pradyotprksh4@gmail.com',
    keywords='basics, blog, firebase',
    data_files=[(
        'data', []
    )],
    packages=find_packages(
        include=[
            'firebase',
            'pyblog',
            'src'
        ]
    ),
    install_requires=[
        'firebase-admin', 'loguru', 'PyInquirer', 'pylint', 'urllib3'
    ],
    entry_points={
        'console_scripts': [
            'pyblog = pyblog:main'
        ]
    }
)
