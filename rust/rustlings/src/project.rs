use glob::glob;
use serde::{Deserialize, Serialize};
use std::env;
use std::error::Error;
use std::path::PathBuf;
use std::process::Command;

/// Contains the structure of resulting rust-project.json file
/// and functions to build the data required to create the file
#[derive(Serialize, Deserialize)]
pub struct RustAnalyzerProject {
    sysroot_src: String,
    pub crates: Vec<Crate>,
}

#[derive(Serialize, Deserialize)]
pub struct Crate {
    root_module: String,
    edition: String,
    deps: Vec<String>,
    cfg: Vec<String>,
}

impl RustAnalyzerProject {
    pub fn new() -> RustAnalyzerProject {
        RustAnalyzerProject {
            sysroot_src: String::new(),
            crates: Vec::new(),
        }
    }

    /// Write rust-project.json to disk
    pub fn write_to_disk(&self) -> Result<(), std::io::Error> {
        std::fs::write(
            "./rust-project.json",
            serde_json::to_vec(&self).expect("Failed to serialize to JSON"),
        )?;
        Ok(())
    }

    /// If path contains .rs extension, add a crate to `rust-project.json`
    fn path_to_json(&mut self, path: PathBuf) -> Result<(), Box<dyn Error>> {
        if let Some(ext) = path.extension() {
            if ext == "rs" {
                self.crates.push(Crate {
                    root_module: path.display().to_string(),
                    edition: "2021".to_string(),
                    deps: Vec::new(),
                    // This allows rust_analyzer to work inside #[test] blocks
                    cfg: vec!["test".to_string()],
                })
            }
        }

        Ok(())
    }

    /// Parse the exercises folder for .rs files, any matches will create
    /// a new `crate` in rust-project.json which allows rust-analyzer to
    /// treat it like a normal binary
    pub fn exercises_to_json(&mut self) -> Result<(), Box<dyn Error>> {
        for path in glob("./exercises/**/*")? {
            self.path_to_json(path?)?;
        }
        Ok(())
    }

    /// Use `rustc` to determine the default toolchain
    pub fn get_sysroot_src(&mut self) -> Result<(), Box<dyn Error>> {
        // check if RUST_SRC_PATH is set
        if let Ok(path) = env::var("RUST_SRC_PATH") {
            self.sysroot_src = path;
            return Ok(());
        }

        let toolchain = Command::new("rustc")
            .arg("--print")
            .arg("sysroot")
            .output()?
            .stdout;

        let toolchain = String::from_utf8_lossy(&toolchain);
        let mut whitespace_iter = toolchain.split_whitespace();

        let toolchain = whitespace_iter.next().unwrap_or(&toolchain);

        println!("Determined toolchain: {}\n", &toolchain);

        self.sysroot_src = (std::path::Path::new(toolchain)
            .join("lib")
            .join("rustlib")
            .join("src")
            .join("rust")
            .join("library")
            .to_string_lossy())
        .to_string();
        Ok(())
    }
}
