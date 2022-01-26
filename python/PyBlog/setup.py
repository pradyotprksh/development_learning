from setuptools import setup, find_packages
import pathlib

here = pathlib.Path(__file__).parent.resolve()

long_description = (here / 'README.md').read_text(encoding='utf-8')

setup(
    name='basics',
    version='1.0.0',
    description='A Python project which will connect to firebase. And as a admin can write a blog',
    long_description=long_description,
    author='Pradyot Prakash',
    author_email='pradyotprksh4@gmail.com',
    keywords='python, firebase, blogs, project',
    data_files=[(
        'data', ['data/']
    )],
    packages=find_packages(
        include=[
            'py_blog',
            'src'
        ]
    ),
    install_requires=[
        'loguru', 'pylint', 'firebase-admin'
    ],
    entry_points={
        'console_scripts': [
            'py_blog = py_blog:main'
        ]
    }
)